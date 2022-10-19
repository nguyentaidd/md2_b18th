package th2;

import java.util.Random;

import static th2.run.DISTANCE;
import static th2.run.STEP;


public class Car implements Runnable{
    //Khai báo tên xe đua
    private String name;

    public Car(String name){
        this.name = name;
    }

    @Override
    public void run() {
        //Khởi tạo điểm bắt đầu
        int runDistance = 0;
        //Khởi tạo thời gian bắt đầu cuộc đua
        long startTime = System.currentTimeMillis();

        //Kiểm tra chừng nào còn xe chưa kết thúc quãng đường đua thì xe vẫn chạy
        while (runDistance < DISTANCE) {
            try {
                //Tốc độ ngẫu nhiên (km/h)
                int speed = (new Random()).nextInt(20);
                //Tính khoảng cách đi
                runDistance += speed;
                //Xây dựng kết quả chung cuộc
                String log = "|";
                int percentTravel = (runDistance * 100) / DISTANCE;
                for (int i = 0; i < DISTANCE; i += STEP) {
                    if (percentTravel >= i + STEP) {
                        log += "=";
                    } else if (percentTravel >= i && percentTravel < i + STEP)
                        log += "o";
                    else
                        log += "=";
                }
                log += "|";
                System.out.println("Car" + this.name + ": " + log + "" + Math.min(DISTANCE, runDistance) + "km");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Car" + this.name + " broken...");
                break;
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Car" + this.name + " Finish in " + (endTime - startTime) / 1000 + "s");
    }
}
