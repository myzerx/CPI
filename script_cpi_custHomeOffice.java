import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import groovy.json.*;

def Message processData(Message message) {
	def bodyVariable = message.getBody(String)
	def jsonFile = new JsonSlurper()
	def jsonObject = jsonFile.parseText(bodyVariable)

	def returnHomeOffice = '', funcionarioHomeOffice = ''

	if (jsonObject.cust_HomeOffice == '') {
		funcionarioHomeOffice
	} else {
		cust_HomeOffice = jsonObject.cust_HomeOffice.cust_HomeOffice
		if (cust_HomeOffice.getClass() == java.util.ArrayList) {
			funcionarioHomeOffice = cust_HomeOffice.cust_HomeOfficeFull
			if (funcionarioHomeOffice == 'S') {
				returnHomeOffice = 'Full Home Office'
			} else {
				returnHomeOffice = 'Hibrido'
			}
		} else {
			funcionarioHomeOffice = cust_HomeOffice.cust_HomeOfficeFull
			if (funcionarioHomeOffice == 'S') {
				returnHomeOffice = 'Full Home Office'
			} else {
				returnHomeOffice = 'Hibrido'
			}
		}
	}

	message.setProperty('pReturnHomeOffice', returnHomeOffice)
	return message;
}