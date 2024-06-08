import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.disposables.Disposable;

public class Hangman {
    public Hangman() {

    }

    public Observable<Output> play(Observable<String> word, Observable<String> guesses) {
        return Observable.create()
    }
}