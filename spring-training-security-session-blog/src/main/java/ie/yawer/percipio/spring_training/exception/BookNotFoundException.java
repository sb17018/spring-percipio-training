package ie.yawer.percipio.spring_training.exception;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(Long id){
        super(String.format("Could not find a book with id: %s", id));
    }
}
