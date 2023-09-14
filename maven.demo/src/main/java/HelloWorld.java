import static spark.Spark.*;

public class HelloWorld {
	public static void main (String[] args) {
		port(4513);
		get("/hello", (request, response) -> "MORRAAA HAHAHAHAHA PAULO");
	}
}
