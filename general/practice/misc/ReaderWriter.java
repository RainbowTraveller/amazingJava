import java.util.List;
import java.util.ArrayList;
import java.lang.Math;
import java.lang.Thread;

public class ReaderWriter {
	public static void main( String [] args ) {
		Writer w  = new Writer();
		System.out.println("Writer Creted");
		Reader r1 = new Reader( w );
		System.out.println("Reader Creted");
		Reader r2 = new Reader( w );
		System.out.println("Reader Creted");
		Reader r3 = new Reader( w );
		System.out.println("Reader Creted");
		Reader r4 = new Reader( w );
		System.out.println("Reader Creted");

		Thread writerThread  = new Thread( w );
		Thread readerThread1 = new Thread( r1 );
		Thread readerThread2 = new Thread( r2 );
		Thread readerThread3 = new Thread( r3 );
		Thread readerThread4 = new Thread( r4 );

		readerThread1.start();
		System.out.println( "Reader Thread Started" );
		readerThread2.start();
		System.out.println( "Reader Thread Started" );
		writerThread.start();
		System.out.println( "Writer Thread Started" );
		readerThread3.start();
		System.out.println( "Reader Thread Started" );
		readerThread4.start();
		System.out.println( "Reader Thread Started" );
	}
}

class Reader implements Runnable {
	private Writer writer;
	private int index;

	public Reader( Writer w ) {
		this.writer = w;
		this.index = 0;
	}

	public void run() {
		while( true ) {
			synchronized( writer ) {
				if( writer.isBufferEmpty() || !writer.isSuitableOffset( index ) ) {
					try {
						System.out.println( "Waiting for Writer to writer a word" );
						writer.wait();
						System.out.println( "Notified ...!" );
						System.out.println( "Contents of Writer : " + writer.getWords() );

					} catch (InterruptedException ie) {
						System.out.println("Inturruption Occurred!");
					}
				}
				System.out.println( "Word Read : " + writer.getWord( index ) );
				index++;
			}
			try {
				Thread.sleep(5000);
			} catch (InterruptedException ie) {
				System.out.println("Inturruption Occurred while Sleeping !");
			}
		}
	}
}

class Writer implements Runnable {

	private List<String> words = new ArrayList<String>();
	private String [] source  = {"Atom", "Uranus", "Mars", "Neptune", "Pluto", "Jupiter", "Venus"};

	public Writer() {
		words = new ArrayList<String>();
	}

	public boolean isBufferEmpty() {
		return words.isEmpty();
	}

	public boolean isSuitableOffset( int index ) {
		return index < words.size();
	}

	public String getWord( int index ) {
		if( index < words.size() )
			return words.get( index );
		return null;
	}

	public List<String> getWords() {
		return words;
	}

	public void addWord( String w ) {
		words.add( w );
	}

	public void run () {
		while (true) {
			synchronized ( this ) {
				int index = (int)( Math.random() * source.length );
				String word = source[ index ];
				addWord( word );
				System.out.println( " Word Written : " +  word );
				System.out.println( words );
				this.notifyAll();
			}
			try {
				Thread.sleep(9000);
			} catch (InterruptedException ie) {
				System.out.println("Inturruption Occurred while Sleeping !");
			}
		}
	}
}
