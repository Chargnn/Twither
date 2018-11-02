package coulombe.twither.Global;

public class ErrorParser {

    public static String parse(String error){
        switch(error){
            case "EmptyFieldException":
            {
                return "Il y a des champs vide";
            }
            case "UserAlreadyExist":{
                return "Cet utilisateur existe déjà";
            }
            case "UserNotFound":{
                return "Utilisateur introuvable";
            }
            case "MessageNotFound":{
                return "Ce message est introuvable";
            }
            default:
            {
                return "Unknown";
            }
        }
    }

}
