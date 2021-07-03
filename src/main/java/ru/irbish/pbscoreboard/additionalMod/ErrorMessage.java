package ru.irbish.pbscoreboard.additionalMod;

public interface ErrorMessage {
    String[] errorMessage = new String[]{"","","","Выберите команду из списка"}; //0 position - tournament name
                                                                                //1 position - calendar data
                                                                                //2 position - team name
                                                                                //3 position - count team for tournament

    default String[]    getErrorMessage(){
        return errorMessage;
    }
    default void        setErrorMessage(String message, Integer position){
        this.errorMessage[position] = message;
    }
}
