public class Singleton {

    private static Object lockObject = new Object();
    private static Integer data;

    public static Integer instance() {
        if(data == null) {
            synchronized (lockObject) {
                if(data == null) {
                    data = loadData();
                }
            }
        }

        return data;
    }

    private static Integer loadData() {
        return 0;
    }
}
