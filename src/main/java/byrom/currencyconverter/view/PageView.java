package byrom.currencyconverter.view;

import byrom.currencyconverter.entity.Currency;
import byrom.currencyconverter.entity.dto.CurrencyConverterDto;
import byrom.currencyconverter.service.CurrencyApi;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Route("")
@Theme(value = Lumo.class, variant = Lumo.DARK)
public class PageView extends VerticalLayout {

    private VerticalLayout mainLayout;
    private NumberField amountForChange;
    private Button searchButton;
    private final CurrencyApi currencyApi;
    private Select<Currency> currencyFrom;
    private Select<Currency> currencyTo;
    private NumberField result;

    public PageView(CurrencyApi currencyApi){
        this.currencyApi = currencyApi;

        mainLayout();
        setHeader();
        setCurrency();

        searchButton.addClickListener(clickEvent -> {
            if (amountForChange.getValue() != null){
                getConversionResult(amountForChange, currencyTo.getValue(), currencyFrom.getValue());
            }
            else {
                Notification notification = Notification.show("Please enter amount",1000, Notification.Position.TOP_CENTER);
            }
        });
    }

    private void getConversionResult(NumberField amountForChange, Currency to, Currency from) {
        CurrencyConverterDto currencyConverter = currencyApi.getCurrencyConverter(to, from, amountForChange.getValue());

        result.setValue(currencyConverter.getResult());
    }

    private void mainLayout() {
        mainLayout = new VerticalLayout();
        mainLayout.setWidth("100%");
        mainLayout.setSpacing(true);
        mainLayout.setMargin(true);
        mainLayout.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);

        add(mainLayout);
    }

    private void setHeader() {
        HorizontalLayout header = new HorizontalLayout();
        header.setDefaultVerticalComponentAlignment(Alignment.BASELINE);

        header.add(new H2("Currency converter"));

        mainLayout.add(header);
    }

    private void setCurrency(){
        HorizontalLayout hl = new HorizontalLayout();
        hl.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        hl.setSpacing(true);
        hl.setMargin(true);

        currencyFrom = new Select<>();
        currencyFrom.setLabel("Currency I Have");
        currencyFrom.setWidth("35%");
        currencyFrom.setItems(Currency.values());
        currencyFrom.setValue(Currency.USD);

//        Amount for change
        amountForChange = new NumberField();
        amountForChange.setStep(0.01);
        amountForChange.setWidth("60%");

        hl.add(currencyFrom, amountForChange);

        HorizontalLayout hl1 = new HorizontalLayout();
        hl1.setDefaultVerticalComponentAlignment(Alignment.BASELINE);

        currencyTo = new Select<>();
        currencyTo.setLabel("Currency I Want");
        currencyTo.setWidth("35%");
        currencyTo.setItems(Currency.values());
        currencyTo.setValue(Currency.USD);

//        Swap two currency
        Button button = new Button(new Icon(VaadinIcon.REFRESH));
        button.addClickListener(change->{
           Currency currency = currencyFrom.getValue();
           currencyFrom.setValue(currencyTo.getValue());
           currencyTo.setValue(currency);
        });

//        Result of conversion
        result = new NumberField();
        result.setWidth("60%");
        result.setStep(0.0001);

        hl1.add(currencyTo, result);

//        Converter button
        searchButton = new Button("Converter!");
        searchButton.setWidth("15%");
        searchButton.setHeight("20%");

        searchButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        searchButton.getStyle().set("margin-top", "55px");
        mainLayout.add(hl,button, hl1, searchButton);
    }
}
