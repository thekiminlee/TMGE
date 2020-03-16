package jfx.game.Library.Bejeweled;
import java.util.ArrayList;


/*
JewelMatchDecorator:
- Used for various JewelMatching operations.
    1. HorizontalMatch
    2. VerticalMatch
 */
public Abstract JewelMatchDecorator implements JewelMatch{

    protected JewelMatch jewelMatch;

    public JewelMatchDecorator(JewelMatch jewelMatch){
        this.jewelMatch = jewelMatch;
        }
}