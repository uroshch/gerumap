package dsw.gerumap.app.gui.swing.controller.mindMapActions;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.gui.swing.errorLogger.EventType;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.messageGenerator.MessageGeneratorImplementation;
import lombok.Getter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
@Getter
public class ConfirmAction implements ActionListener {

    private Color color;
    private int lineStroke;
    private String text2;
    @Override
    public void actionPerformed(ActionEvent e) {
        String text = MainFrame.getInstance().getActionManager().getColorChooserAction().getTextField().getText();
        text2 = MainFrame.getInstance().getActionManager().getColorChooserAction().getTextFieldText().getText();
        if(!text.matches("[0-9]+") || !text2.matches("[0-9a-zA-Z]+"))
        {
            ((MessageGeneratorImplementation) ApplicationFramework.getInstance().getMessageGenerator()).setType(EventType.CANNOT_SET_NAME);
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage();
            MainFrame.getInstance().getActionManager().getColorChooserAction().getTextField().setText("");
            MainFrame.getInstance().getActionManager().getColorChooserAction().getTextFieldText().setText("");

            return;
        }
          lineStroke = Integer.parseInt(text);
          color = MainFrame.getInstance().getActionManager().getColorChooserAction().getColorChooser().getColor();
        System.out.println(color);
        MainFrame.getInstance().getActionManager().getColorChooserAction().getFrame().dispose();

    }
}