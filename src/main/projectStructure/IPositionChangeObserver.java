package projectStructure;

import features.Vector2D;

public interface IPositionChangeObserver {
    void positionChanged(Vector2D oldPosition, Vector2D newPosition, Animal animal);
}
