package tr.ogm.gov.krestakip.controller;

import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import tr.ogm.gov.krestakip.model.entity.Ogrenci;
import tr.ogm.gov.krestakip.model.service.AbstractOgrenciService;
import tr.ogm.gov.krestakip.view.MessagesView;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

@Component(value = "ogrenciController")
@Scope("view")
public class OgrenciController implements Serializable
{
    @Getter
    @Setter
    private List<Ogrenci> filteredOgrenciList;
    @Getter
    @Setter
    private List<Ogrenci> ogrenciList;
    @Getter
    @Setter
    private Ogrenci ogrenci;


    @Autowired
    private AbstractOgrenciService abstractOgrenciService;

    @PostConstruct
    public void init() {
        refresh();
        this.ogrenciList = abstractOgrenciService.findAll();
    }

    public void fetchAll() {
        this.ogrenciList = abstractOgrenciService.findAll();

    }

    public void refresh()
    {
        ogrenci = new Ogrenci();
    }

    public void save()
    {
        abstractOgrenciService.save(ogrenci);
        MessagesView.saveMessage();
        refresh();
        this.ogrenciList=abstractOgrenciService.findAll();
    }

    public void delete(Ogrenci ogrenci) {
        abstractOgrenciService.deleteByID(ogrenci.getId());
        MessagesView.deleteMessage();
        refresh();
        this.ogrenciList = abstractOgrenciService.findAll();
    }

    public void onRowEdit(RowEditEvent<Ogrenci> event) {
        Ogrenci updateOgrenci = event.getObject();
        abstractOgrenciService.update(updateOgrenci);
        MessagesView.updateMessage();
        this.ogrenciList = abstractOgrenciService.findAll();
    }
    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (filterText == null || filterText.equals("")) {
            return true;
        }
        int filterInt = getInteger(filterText);
        Ogrenci ogrenci = (Ogrenci) value;
        DateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
        String tarih= formatter.format(ogrenci.getKayitTarihi());
        return ogrenci.getKayitNo()==filterInt|| ogrenci.getAd()
                .toLowerCase().contains(filterText) || ogrenci.getSoyad().toLowerCase().contains(filterText)
                || ogrenci.getCinsiyet().toLowerCase().contains(filterText) || tarih.contains(filterText)
                || ogrenci.getTelefon() == filterInt
                || ogrenci.getYetki().toLowerCase().contains(filterText)
                || ogrenci.getTc()==filterInt
                || ogrenci.getAdres().toLowerCase().contains(filterText);
    }

    private int getInteger(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }
    public void onRowCancel(RowEditEvent<Ogrenci> event) {
        MessagesView.canceledMessage();
    }

}
