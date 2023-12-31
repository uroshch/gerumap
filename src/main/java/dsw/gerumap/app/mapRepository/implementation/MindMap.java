package dsw.gerumap.app.mapRepository.implementation;
import dsw.gerumap.app.mapRepository.command.CommandManager;
import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.composite.MapNodeComposite;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

public class MindMap extends MapNodeComposite {

    private boolean template;
    private int numberOfChildren = 0;
    private String filePath;

    private transient CommandManager commandManager;

    public MindMap(String name, MapNode parent) {
        super(name, parent);
        commandManager = new CommandManager();
    }

    public MindMap()
    {
        this.settName();
        commandManager = new CommandManager();
    }
    @Override
    public void addChild(MapNode child) {
        if(child instanceof Element)
        {
            if(getChildren().contains(child))
                return;
            getChildren().add((Element) child);
            child.setParent(this);
            numberOfChildren++;
            this.notifySubscribers(this);
            ((Project)this.getParent()).setChanged(true);
        }
    }

    private void settName()
    {
        this.setName("MindMap");
    }

    @Override
    public void setName(String name) {
        super.setName(name);
        this.notifySubscribers(this);
    }
    @Override
    public void deleteChild(MapNode child) {
        ((Project)this.getParent()).setChanged(true);
        super.deleteChild(child);
        this.notifySubscribers(this);


    }
}
