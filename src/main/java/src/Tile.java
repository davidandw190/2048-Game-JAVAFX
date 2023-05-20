package src;

import javafx.scene.control.Label;

import java.text.BreakIterator;

public class Tile extends AbstractTile {

    private int value;
    private int transition;
    private Label valueLabel;
    boolean isCombination;


    public Tile() {
        super();
        square.getStyleClass().add("tile-empty");
        value = 0;

        this.valueLabel = new Label();
        valueLabel.getStyleClass().addAll(
                "tile-label", "tile-label-black", "tile-label-one digit");

        this.getChildren().add(valueLabel);
    }

    public Tile(int value) {
        super();
        String styleClass = "tile-" + Integer.toString(value);
        square.getStyleClass().add(styleClass);
        this.value = value;
        this.valueLabel = new Label();
        valueLabel.setText((Integer.toString(value)));

        String color = value > 4 ? "white" : "black";
        String labelTextColorStyleClass = "tile-label-" + color;
        int numOfDigitsInValue = String.valueOf(value).length();
        String labelTextSizeStyleClass = "tile-label" +  Integer.toString(numOfDigitsInValue);
        valueLabel.getStyleClass().addAll("tile-label", labelTextColorStyleClass, labelTextSizeStyleClass);

        this.getChildren().add(valueLabel);

    }


    public boolean isCombination(){
        return isCombination;
    }

    public void wasCombinated(){
        this.isCombination = true;
    }

    public void resetIsCombination(){
        this.isCombination = false;
    }


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;

    }

    public void updateValueLabel() {
        String newStyleClass = "tile-" + Integer.toString(this.value);

        square.getStyleClass().remove(1);
        square.getStyleClass().add(newStyleClass);
        valueLabel.getStyleClass().remove(1, 3);

        String color = "black";
        if (this.value > 4) { color = "white"; }

        String labelTextColorStyleClass = "tile-label-" + color;
        int numOfDigitsInValue = String.valueOf(this.value).length();
        String labelTextSizeStyleClass = "tile-label-" + Integer.toString(numOfDigitsInValue);

        valueLabel.getStyleClass().addAll("tile-label", labelTextColorStyleClass, labelTextSizeStyleClass);

        this.valueLabel.setText(Integer.toString(this.value));

    }

    public void newValue() {
        square.getStyleClass().remove("tile-empty");

        if ( Math.random() < 0.9 ) {
            square.getStyleClass().add("tile-2");
            this.value = 2;

        } else {
            square.getStyleClass().add("tile-4");
            this.value = 4;

        }

        updateValueLabel();
    }

    public int getTransition() {
        return transition;
    }

    public void setTransition(int transition) {
        this.transition = transition;
    }

    public BreakIterator getValueLabel() {
    }
}
