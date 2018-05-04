package com.panda.ReflectDemo.xicheng;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.ReflectionUtils;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author 18048474
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class ReflectTest {

    public static void main(String[] args) throws IllegalAccessException {
        String s = "{\"Head\":{\"Language\":\"ENGLISH\",\"Source\":\"ANDROID\",\"Currency\":\"CNY\",\"PaymentCurrency\":\"CNY\",\"PaymentCurrencyList\":[\"CNY\"],\"Version\":\"String\",\"UID\":\"String\",\"VID\":\"String\",\"Token\":\"String\",\"TokenValidTime\":\"String\",\"IP\":\"String\",\"IsQuickBooking\":0,\"ClientID\":\"String\",\"DeviceID\":\"String\",\"APIKey\":\"String\",\"ClientSign\":\"String\",\"ClientSignTime\":0,\"ServiceCode\":\"String\",\"Site\":\"HK_EN\",\"Ticket\":\"String\",\"Locale\":\"String\",\"Group\":\"String\",\"Union\":{\"AllianceID\":0,\"SID\":0,\"OUID\":\"String\"}},\"PackageFareId\":\"String\",\"ReturnPackageFareId\":\"String\",\"ProductIdList\":[\"String\"],\"TicketingOptionSelected\":\"String\",\"PassengerList\":[{\"FirstName\":\"String\",\"LastName\":\"String\",\"Gender\":0,\"CertType\":0,\"CertNo\":\"String\",\"Age\":0,\"Birthday\":\"String\",\"Count\":0,\"CountryOfResidence\":\"String\",\"Nationality\":\"String\",\"IsLeader\":false,\"PassengerType\":0}],\"Contact\":{\"FirstName\":\"String\",\"LastName\":\"String\",\"Email\":\"String\",\"PhoneCountryCode\":\"String\",\"PhoneNumber\":\"String\"},\"PreferenceList\":[{\"SegmentId\":0,\"Type\":0,\"Name\":\"String\",\"OptionList\":[\"String\"]}],\"RmsToken\":\"String\",\"BizType\":\"String\"}";
        JsonRootBean jsonRootBean = JSONObject.parseObject(s, JsonRootBean.class);
        List list = ReflectUtil.fieldToList(jsonRootBean.getClass(), "",jsonRootBean);
        Object ticketingOptionSelected = ReflectUtil.getObjectValue(jsonRootBean, ReflectionUtils.findField(jsonRootBean.getClass(), "PassengerList"));
        String s1 = JSONObject.toJSONString(list);
        System.out.println(1);
    }
}
