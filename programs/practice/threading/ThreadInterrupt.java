import java.util.concurrent.atomic.AtomicBoolean;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.BufferedWriter;
import java.nio.channels.ClosedByInterruptException;
import java.io.IOException;

public class ThreadInterrupt {
    public static void main(String[] args) {
		AtomicBoolean thread1Done = new AtomicBoolean(false);

		//write in file
		Thread thread1 = new Thread(() -> {

			try(BufferedWriter writer = Files.newBufferedWriter(Paths.get("foo.txt"))){
				for(int i = 0; i < 10000; i++){
					if(Thread.currentThread().isInterrupted())
						Thread.interrupted();
					writer.write(i);
					if(Thread.currentThread().isInterrupted())
						Thread.interrupted();
					writer.newLine();
				}
			} catch(ClosedByInterruptException cbie) {
				cbie.printStackTrace();
			} catch(IOException ie) {
				ie.printStackTrace();
			}

			thread1Done.set(true);

		});

		//interrupt thread1
		Thread thread2 = new Thread(() -> {

			while(!thread1Done.get()){
				thread1.interrupt();
			}

		});

		thread2.start();
		thread1.start();
    }
}

