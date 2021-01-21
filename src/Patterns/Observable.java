package Patterns;

public interface Observable {
    void inform(PieceOfInformation pieceOfInformation);
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
}
