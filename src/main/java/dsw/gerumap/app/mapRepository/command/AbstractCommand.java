package dsw.gerumap.app.mapRepository.command;

public abstract class AbstractCommand {
    public abstract void doCommand();

    public abstract void undoCommand();
}
