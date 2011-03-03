package toer

/**
 * Created by te - Date: 2/26/11
 */
enum Command {
    moveMouse,mousePress

    void setCommand(String command) {
        String[] commandSplitted = command.split(" ")
        println "" +commandSplitted[0]
        1..commandSplitted.size() { i ->
            String argument = commandSplitted[i]
            println argument
        }
    }

}
