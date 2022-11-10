import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;



def Message processData(Message message) {
    
    def body = message.getBody(java.lang.String) as String;
    def property = message.getProperties();
    def header = message.getHeaders() as String;
    def ExceptionCaught = property.get("CamelExceptionCaught") as String;
    def SAP_MonitoringStateProperties = property.get("SAP_MonitoringStateProperties") as String;
    def properties = message.getProperties();
    def logConfig = properties.get("SAP_MessageProcessingLogConfiguration");
    def logConfigLevel = (String) logConfig.logLevel;
    def messageLog = messageLogFactory.getMessageLog(message);
    
    if (messageLog != null && (logConfigLevel.equals('INFO') || logConfigLevel.equals('TRACE')) ){
            messageLog.addAttachmentAsString("Log PayloadFinal:", body, "text/plain"); // mostra o que está sendo passado no momento da execução
    }
    
    if ( ExceptionCaught != null || ExceptionCaught == '') {
            messageLog.addAttachmentAsString("Log Erro:", ExceptionCaught, "text/plain"); // pega o erro da execução java
    }
    
    return message;
}