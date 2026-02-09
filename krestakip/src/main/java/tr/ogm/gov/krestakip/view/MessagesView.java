package tr.ogm.gov.krestakip.view;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MessagesView
{
    public static void saveMessage() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Başarılı", "Kayıt eklendi."));
    }
    public static void deleteMessage() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Başarılı", "Kayıt silindi."));
    }
    public static void updateMessage() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Başarılı", "Kayıt güncellendi."));
    }
    public static void canceledMessage() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bilgi", "İptal edildi."));
    }

    public static void saveNotMessage() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hata!", "Kayıt yapılamadı."));
    }

    public static void warningMessage() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Uyarı", "Kayıt bulunamadı."));
    }

    public static void errorMessage(String message) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hata", message));
    }
}
