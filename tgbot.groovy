import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import groovyx.net.http.HttpBuilder;
import static groovyx.net.http.HttpBuilder.configure;

class TelegramBot {
    def botToken
    def chatId

        TelegramBot(String botToken, String chatId){
            this.botToken = botToken
            this.chatId = chatId
        }

            def sussefulMessage = { mesage -> 
                postMessage("susseful" + message)   
            }
            def errorMessage = { mesage -> 
                postMessage("error" + message)
            }
    

    def mobilemessage(message){
        message = "Mobile" + message
        return message
    }

    private def postMessage(message){
        def http = new HTTPBuilder("https://api.telegram.org/bot${botToken}/sendMessage${chatId}")
            http.request(Method.POST){
                headers.'Content-Type' = 'application/json'
                body = JsonOutput.toJson([
                    chat_id: chatId,
                    text: message 
                ])
            }
    }
}
                withCredentials([usernamePassword(
                credentialsId: 'telegrambot', 
                passwordVariable: 'tgpass', 
                usernameVariable: 'tgtoken'
                )]) {
                def bot = new TelegramBot(botToken, chatId)
                }

bot.sussefulMessage("message")
