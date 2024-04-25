package com.example.rxjava.service;

//import com.example.rxjava.repository.MyRepository;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.Flow;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

@Service
public class MyService {


//    public Observable<String> fetchData() {
//        Observable<String> observable = Observable.defer(() -> {
//            // Giả lập việc tạo Observable mỗi khi có Observer đăng ký
//            return Observable.just("a","b","c","d");
//        });
//        observable.subscribe(result -> System.out.println("Result: " + result));
//        return observable;
//    }


    public Observable<String> fetchData() {
        Observable<Integer> observable = Observable.create(emitter -> {
            // Phát ra các số nguyên từ 1 đến 5
            for (int i = 1; i <= 5; i++) {
                emitter.onNext(i); // Phát ra một số nguyên
            }
            emitter.onComplete(); // Phát tín hiệu hoàn thành
        });

        observable.subscribe(
                value -> System.out.println("Received: " + value), // Người nghe onNext
                error -> System.err.println("Error occurred: " + error), // Người nghe onError
                () -> System.out.println("Completed") // Người nghe onComplete
        );


        Observable<Long> values = Observable.interval(1000, TimeUnit.MILLISECONDS);
        values.subscribe(
                v -> System.out.println("Interval Received: " + v),
                e -> System.out.println("Error: " + e),
                () -> System.out.println("Completed")
        );
        Observable<String> resulty = Observable.zip(observable, values, (x, y) -> "start " + x + " -:- " + y + "end");
        resulty.subscribe(System.out::println);



        FutureTask<Integer> f = new FutureTask<Integer>(() -> {
            Thread.sleep(2000);
            return 21;
        });
        new Thread(f).start();




        Observable<String> observable2 = Observable.just("Hello", "RxJava");

        Observable<String> source1 = Observable.just("One", "Two", "Three", "Four", "Five")
                .subscribeOn(Schedulers.io());
        Observable<Integer> source2 = Observable.just(1, 2, 3,4)
                .subscribeOn(Schedulers.computation());

        Observable<String> resultx = Observable.zip(source1, source2, (s, i) -> s + ": " + i);

        resultx.subscribe(System.out::println);



        observable2.subscribe(result -> System.out.println("Result: " + result));


        return observable2;
    }





//    public Observable<String> fetchData() {
//        Observable<String> observable = Observable.just("Hello", "RxJava");
//        observable.subscribe(result -> System.out.println("Result: " + result));
//        return observable;
//    }



//    public Observable<String[]> fetchData() {
//        Observable<String[]> observable = Observable.fromCallable(() -> {
//            String[] data = {"a","b","c","d"};
//            // Giả lập việc tạo Observable mỗi khi có Observer đăng ký
//            return data;
//        });
//        observable.subscribe(result -> System.out.println("Result: " + result));
//        return observable;
//    }



//    public Observable<String> fetchData() {
//        return Observable.fromCallable(() -> {
//            return "Data";
//        }).subscribeOn(Schedulers.io());
//    }
}
