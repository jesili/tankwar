package cor;

import com.company.GameObject;

public interface Collider {
    boolean collide(GameObject o1, GameObject o2);
}
