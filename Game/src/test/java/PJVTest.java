import cz.cvut.fel.pjv.Objects.Block;
import cz.cvut.fel.pjv.Objects.Enemy;
import cz.cvut.fel.pjv.Objects.Player;
import cz.cvut.fel.pjv.Objects.Weapon.Axe_Weapon;
import cz.cvut.fel.pjv.Objects.Weapon.Weapons;
import cz.cvut.fel.pjv.framework.FileReader;
import cz.cvut.fel.pjv.framework.GameObject;
import cz.cvut.fel.pjv.framework.MouseInput;
import cz.cvut.fel.pjv.framework.ObjectID;
import cz.cvut.fel.pjv.window.Handler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.FileNotFoundException;

class PJVTest {
    Handler handler = new Handler();
    FileReader file;

    {
        try {
            file = new FileReader();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    void CreateFakeGame(){
        handler.addObject(new Player(0, 0, ObjectID.Player));
        handler.addObject(new Enemy(100, 0, ObjectID.Enemy));
        handler.addObject(new Block(200, 0, ObjectID.Block));
        handler.addObject(new Axe_Weapon(300, 0,0,handler, ObjectID.Player));
    }



    @Test
    void TestPlayerHearts(){

        Assertions.assertEquals(Player.Hearts, file.getHearts());
        Assertions.assertEquals(file.getHearts(), 4);
        Assertions.assertEquals(Player.Hearts, 4);
        Player.Hearts --;
        Assertions.assertEquals(Player.Hearts,3);
    }

    @Test
    void TestPlayerMoney(){
        CreateFakeGame();

        Assertions.assertEquals(Player.Money, file.getMoney());
        Assertions.assertEquals(Player.Money, 0);

        Player.Money ++;
        Assertions.assertEquals(Player.Money, 1);
    }

    @ParameterizedTest
    @ValueSource(ints = {45, 76, 24, 87, 453, 21, 6, 78})
    void TestPlayerPosition(int xy){
        CreateFakeGame();
        int x = 0;
        int y = 0;
        for (int i = 0; i < handler.object.size(); i++) {

            GameObject obj = handler.object.get(i);
            if(obj != null) {
                if (obj.getId() == ObjectID.Player) {
                    obj.setX(xy);
                    obj.setY(xy);
                    y = obj.getY();
                    x = obj.getX();
                }
            }
        }
        Assertions.assertEquals(x,xy);
        Assertions.assertEquals(y,xy);
    }

    @Test
    void TestShop(){

        Player.Money = 100;

        MouseInput.Shop(1);
        Assertions.assertEquals(Player.state,Weapons.Axe);
        Assertions.assertNotEquals(Player.state,Weapons.Spear);

        MouseInput.Shop(2);
        Assertions.assertEquals(Player.state,Weapons.Spear);
        Assertions.assertNotEquals(Player.state,Weapons.Axe);

        Assertions.assertEquals(file.getHearts(), Player.Hearts);
        MouseInput.Shop(3);
        Assertions.assertNotEquals(file.getHearts(), Player.Hearts);

        Assertions.assertEquals(file.getSpeed(), Player.Speed);
        MouseInput.Shop(4);
        Assertions.assertNotEquals(file.getSpeed(), Player.Speed);

        Assertions.assertNotEquals(Player.Money,100);
        Assertions.assertEquals(Player.Money, 70);

    }


}