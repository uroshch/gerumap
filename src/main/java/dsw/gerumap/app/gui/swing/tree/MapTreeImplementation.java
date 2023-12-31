package dsw.gerumap.app.gui.swing.tree;

import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.tree.view.MapTreeView;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.MindMapView;
import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.composite.MapNodeComposite;
import dsw.gerumap.app.mapRepository.factory.FactoryUtils;
import dsw.gerumap.app.mapRepository.implementation.*;


import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

public class MapTreeImplementation implements MapTree {

    private MapTreeView treeView;
    private DefaultTreeModel treeModel;

    @Override
    public MapTreeView generateTree(ProjectExplorer projectExplorer) {
        MapTreeItem root = new MapTreeItem(projectExplorer);
        treeModel = new DefaultTreeModel(root);
        treeView = new MapTreeView(treeModel);
        return treeView;
    }

    @Override
    public void addChild(MapTreeItem parent) {

        if (!(parent.getMapNode() instanceof MapNodeComposite))
            return;

        MapNode child = createChild(parent.getMapNode());
        parent.add(new MapTreeItem(child));
        if(child instanceof MindMap || child instanceof Element)
            child.setName(child.getName() + String.valueOf(((MapNodeComposite) parent.getMapNode()).getNumberOfChildren()+1));

        ((MapNodeComposite) parent.getMapNode()).addChild(child);
        if(child instanceof Project)
            child.setName(child.getName() + String.valueOf(((MapNodeComposite) parent.getMapNode()).getNumberOfChildren()));
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public void removeChild(MapTreeItem child) {

        ((MapNodeComposite) child.getMapNode().getParent()).deleteChild(child.getMapNode());
        MainFrame.getInstance().getProjectExplorer().setSelectionPath(null);
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);

    }

    @Override
    public MapTreeItem getSelectedNode() {
        return (MapTreeItem) treeView.getLastSelectedPathComponent();
    }

    @Override
    public void loadProject(Project project) {
        MapTreeItem loadedProject = new MapTreeItem(project);
        ((MapTreeItem)treeModel.getRoot()).add(loadedProject);

        MapNodeComposite mapNode = (MapNodeComposite) ((MapTreeItem)treeModel.getRoot()).getMapNode();
        mapNode.addChild(project);
        project.setParent(mapNode);
        for(MapNode mapNode1 : project.getChildren())
        {
            mapNode1.setParent(project);
            MapTreeItem mapTreeItem = new MapTreeItem(mapNode1);
            for (MapNode element : ((MapNodeComposite)mapNode1).getChildren()){
                element.setParent(mapNode1);
                if(element instanceof Connection){
                    for(MapNode concept : ((MapNodeComposite) mapNode1).getChildren()){
                        if(concept instanceof Concept && ((Concept) concept).getPosition().equals(((Connection) element).getFirstConcept().getPosition())){
                            ((Connection) element).setFirstConcept((Concept) concept);
                        } else if (concept instanceof Concept && ((Concept) concept).getPosition().equals(((Connection) element).getSecondConcept().getPosition())) {
                            ((Connection) element).setSecondConcept((Concept) concept);
                        }
                    }
                }
            }
            loadedProject.add(mapTreeItem);
        }
        
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }


    private MapNode createChild(MapNode parent) {
        return FactoryUtils.getFactory(parent).getNode();

    }

}
