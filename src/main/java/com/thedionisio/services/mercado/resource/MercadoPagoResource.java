package com.thedionisio.services.mercado.resource;

import com.thedionisio.services.mercado.ctrl.MercadoPagoCtrl;
import com.thedionisio.services.mercado.dto.GetPreference;
import com.thedionisio.services.mercado.dto.Items;
import com.thedionisio.services.mercado.dto.Preference;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jonathan on 6/14/17.
 */

@RestController()
@RequestMapping(value = "mercado-pago")
public class MercadoPagoResource {

    private static String returnString;
    private static String json;


    @RequestMapping(value="/checkout", method = RequestMethod.POST)
    @ResponseBody
    public String resposeJson(@RequestBody GetPreference GetPreference) throws Exception {

        System.out.println("\n\n\n\n\n\n");
        MercadoPagoCtrl mp = new MercadoPagoCtrl ("6590324465267890", "i9qaXLtxy6yncdrwW0Tv0TSkCz1mZyUK");

        Preference preference = new Preference();
        preference = GetPreference.preference;
        System.out.println("\n\n\n\n\n\n");

        System.out.println(preference.payer.email);



        String item = "";

        for(Items i : preference.items){

            item += "{'title':'"+ i.title + "item 1'," +
                    "'quantity':"+i.quantity+"," +
                    "'currency_id':'"+i.currency_id+"'," +
                    "'unit_price':"+i.unit_price+"},";
        }

        System.out.println(item + "\n\n");

        JSONObject createPreferenceResult = mp.createPreference("{'items':[" + item+ "]," +
                "'payer':{'name': '" + preference.payer.name + "'," +
                "'email': '" + preference.payer.email + "'}}");



        System.out.print(createPreferenceResult.get("response").toString());
        returnString = createPreferenceResult.get("response").toString();
        return returnString;
    }


}
