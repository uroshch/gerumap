package dsw.gerumap.app.gui.serializer;

import dsw.gerumap.app.core.Serializer;
import dsw.gerumap.app.mapRepository.implementation.Project;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GsonSerializer implements Serializer {

    //private final Gson gson = new Gson();
    @Override
    public Project loadProject(File file) {
        /*try (FileReader fileReader = new FileReader(file)) {
            return gson.fromJson(fileReader, Project.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }*/
        return null;
    }

    @Override
    public void saveProject(Project project) {
       /* try (FileWriter writer = new FileWriter(project.getFilePath())) {
            gson.toJson(project, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}