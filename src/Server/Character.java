package Server;

public class Character {
    private boolean enabled;
    private String colour;
    private int locationX;
    private int locationY;

    public Character() {
        enabled = false;
    }


    //setters
    public void setLocation(int x, int y) {
        locationX = x;
        locationY = y;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public  void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    //getters
    public boolean isEnabled() {
        return enabled;
    }
    public int getLocationX() {
        return locationX;
    }

    public int getLocationY() {
        return locationY;
    }

    public String getColour() {
        return colour;
    }
}
