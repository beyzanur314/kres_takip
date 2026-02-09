package tr.ogm.gov.krestakip.util.scope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import javax.faces.context.FacesContext;
import javax.faces.event.PreDestroyViewMapEvent;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import java.util.*;

public class ViewScope implements Scope, HttpSessionBindingListener {

    private final WeakHashMap<HttpSession, Set<ViewScopeViewMapListener>> sessionToListeners = new WeakHashMap<>();

    @SuppressWarnings("unchecked")
    @Override
    public Object get(String name, ObjectFactory objectFactory) {
        Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
        if (viewMap.containsKey(name)) {
            return viewMap.get(name);
        } else {
            Map lruMap = (Map) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("com.sun.faces.application.view.activeViewMaps");
            if (lruMap != null && !lruMap.isEmpty() && lruMap.size() > 1) {
                Iterator itr = lruMap.entrySet().iterator();
                if (itr.hasNext()) {
                    Map.Entry entry = (Map.Entry) itr.next();
                    Map<String, Object> map = (Map<String, Object>) entry.getValue();
                    map.clear();
                    itr.remove();
                }
            }
            Object object = objectFactory.getObject();
            viewMap.put(name, object);
            return object;
        }
    }

    @Override
    public String getConversationId() {
        return null;
    }

    @Override
    public Object remove(String name) {
        Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
        if (viewMap.containsKey(name)) {
            Object removed;
            synchronized (viewMap) {
                if (viewMap.containsKey(name)) {
                    removed = FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove(name);
                } else {
                    return null;
                }
            }

            HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            Set<ViewScopeViewMapListener> sessionListeners;
            sessionListeners = sessionToListeners.get(httpSession);
            if (sessionListeners != null) {
                Set<ViewScopeViewMapListener> toRemove = new HashSet<>();
                for (ViewScopeViewMapListener listener : sessionListeners) {
                    if (listener.getName().equals(name)) {
                        toRemove.add(listener);
                        FacesContext.getCurrentInstance().getViewRoot().unsubscribeFromViewEvent(PreDestroyViewMapEvent.class, listener);
                    }
                }
                synchronized (sessionListeners) {
                    sessionListeners.removeAll(toRemove);
                }
            }

            return removed;
        }
        return null;
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
    /*    logger.trace("registerDestructionCallback for bean " + name);

        UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
        ViewScopeViewMapListener listener = new ViewScopeViewMapListener(viewRoot, name, callback, this);
        viewRoot.subscribeToViewEvent(PreDestroyViewMapEvent.class, listener);
        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        final Set<ViewScopeViewMapListener> sessionListeners;
        if (sessionToListeners.containsKey(httpSession)) {
            sessionListeners = sessionToListeners.get(httpSession);
        } else {
            synchronized (sessionToListeners) {
                if (sessionToListeners.containsKey(httpSession)) {
                    sessionListeners = sessionToListeners.get(httpSession);
                } else {
                    sessionListeners = new HashSet<>();
                    sessionToListeners.put(httpSession, sessionListeners);
                }
            }
        }
        synchronized (sessionListeners) {
            sessionListeners.add(listener);
        }
        if (!FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey("sessionBindingListener")) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("sessionBindingListener", this);
        }*/
    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        final Set<ViewScopeViewMapListener> listeners;
        synchronized (sessionToListeners) {
            if (sessionToListeners.containsKey(event.getSession())) {
                listeners = sessionToListeners.get(event.getSession());
                sessionToListeners.remove(event.getSession());
            } else {
                listeners = null;
            }
        }
        if (listeners != null) {
            // I just hope that JSF context already done this job
            for (ViewScopeViewMapListener listener : listeners) {
                // As long as our callbacks can run only once - this is not such big deal
                listener.doCallback();
            }
        }
    }

    /**
     * Will remove listener from session set and unregister it from UIViewRoot.
     */
    public void unregisterListener(ViewScopeViewMapListener listener) {
        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        FacesContext.getCurrentInstance().getViewRoot().unsubscribeFromViewEvent(PreDestroyViewMapEvent.class, listener);
        if (httpSession != null) {
            synchronized (sessionToListeners) {
                if (sessionToListeners.containsKey(httpSession)) {
                    sessionToListeners.get(httpSession).remove(listener);
                }
            }
        }
    }
}