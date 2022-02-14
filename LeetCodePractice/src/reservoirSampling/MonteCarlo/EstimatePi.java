package reservoirSampling.MonteCarlo;

import java.util.Random;
/*
estimate the ratio of a circle's circumference to its diameter pi by monte Carlo method master theorem.

analysis:
for the first quadrant
the 1/4 of circle area is pi * R * R / 4 = pi / 4; R = 1
the square area is 1 * 1 = 1
so the area ratio between 1/4 of circle and square is: inside / total samples = pi / 4 / 1
 */
public class EstimatePi {
    Random rand = new Random();
    public double estimate(int count){
        int inside = 0;
        for(int i = 0; i < count; i++){
            double x = rand.nextDouble();
            double y = rand.nextDouble();
            if(x * x + y * y <= 1.0){
                inside++;
            }
        }
        double pi = 4.0 * inside / count;
        return pi;
    }
}
