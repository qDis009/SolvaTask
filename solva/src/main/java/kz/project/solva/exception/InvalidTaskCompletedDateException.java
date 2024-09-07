package kz.project.solva.exception;

public class InvalidTaskCompletedDateException extends RuntimeException {
  public InvalidTaskCompletedDateException(String message) {
    super(message);
  }
}
