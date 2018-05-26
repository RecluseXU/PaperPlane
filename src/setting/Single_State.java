package setting;

public final class Single_State {//状态表
	private static Single_State instance = null;
	
	//登陆
	private static boolean login = false;
	private static String account;
	private static boolean noInternet = false;//是否是单机模式
	//纸飞机飞出宿舍动画
	private static boolean Animation_FlyOutOfTheDorm_running = true;
	//界面切换
	private static boolean exit_game_frame = false;
	private static boolean exit_all = false;
	//关卡
	private static boolean mission_one_running = true;
	private static boolean mission_one_complete = false;
	private static int level;//难度
	private static int against;//逆风
	//关卡内部
	private static double now_X = 0;//实际上的X坐标
	private static double now_y = 0;//实际上的Y坐标
	//暂停界面
	private static boolean pause = false;
	private static boolean return_game = false;
	private static boolean return_initial_interface = false;
	//黑色过度
	private static boolean changing_background_start = true;
	private static boolean changing_background_end = false;
	private static boolean changing_background_allend = false;
	//滑稽风暴
	private static boolean cyclone_happen = false;
	//背景移动
	private static boolean passing_background_runing = true;
	//飞机
	private static int plane_speed = 12;//速度
	private static boolean plane_lock = false;//飞机锁定
	//分数
	private static int all_point = 0;
	
	
	
	

	
	public static boolean isNoInternet() {
		return noInternet;
	}
	public static void setNoInternet(boolean noInternet) {
		Single_State.noInternet = noInternet;
	}
	public static int getAll_point() {
		return all_point;
	}
	public static void setAll_point(int all_point) {
		Single_State.all_point = all_point;
	}
	public static boolean isReturn_initial_interface() {
		return return_initial_interface;
	}
	public static void setReturn_initial_interface(boolean return_initial_interface) {
		Single_State.return_initial_interface = return_initial_interface;
	}
	public static boolean isReturn_game() {
		return return_game;
	}
	public static void setReturn_game(boolean return_game) {
		Single_State.return_game = return_game;
	}
	public static boolean isPause() {
		return pause;
	}
	public static void setPause(boolean pause) {
		Single_State.pause = pause;
	}
	public static int getAgainst() {
		return against;
	}
	public static void setAgainst(int against) {
		Single_State.against = against;
	}
	public static String getAccount() {
		return account;
	}
	public static void setAccount(String account) {
		Single_State.account = account;
	}
	public static boolean isLogin() {
		return login;
	}
	public static void setLogin(boolean login) {
		Single_State.login = login;
	}
	public static boolean isExit_all() {
		return exit_all;
	}
	public static void setExit_all(boolean exit_all) {
		Single_State.exit_all = exit_all;
	}
	public static boolean isExit_game_frame() {
		return exit_game_frame;
	}
	public static void setExit_game_frame(boolean exit_game_frame) {
		Single_State.exit_game_frame = exit_game_frame;
	}
	public static int getLevel() {
		return level;
	}
	public static void setLevel(int level) {
		Single_State.level = level;
	}
	public static double getNow_y() {
		return now_y;
	}
	public static void setNow_y(double now_y) {
		Single_State.now_y = now_y;
	}
	public static int getPlane_speed() {
		return plane_speed;
	}
	public static void setPlane_speed(int plane_speed) {
		Single_State.plane_speed = plane_speed;
	}
	public static double getNow_X() {
		return now_X;
	}
	public static void setNow_X(double now_X) {
		Single_State.now_X = now_X;
	}
	public static boolean isMission_one_running() {
		return mission_one_running;
	}
	public static void setMission_one_running(boolean mission_one_running) {
		Single_State.mission_one_running = mission_one_running;
	}
	public static boolean isMission_one_complete() {
		return mission_one_complete;
	}
	public static void setMission_one_complete(boolean mission_one_complete) {
		Single_State.mission_one_complete = mission_one_complete;
	}
	public static boolean isPlane_lock() {
		return plane_lock;
	}
	public static void setPlane_lock(boolean plane_lock) {
		Single_State.plane_lock = plane_lock;
	}
	public static boolean isPassing_background_runing() {
		return passing_background_runing;
	}
	public static void setPassing_background_runing(boolean passing_background_runing) {
		Single_State.passing_background_runing = passing_background_runing;
	}
	public static boolean isChanging_background_start() {
		return changing_background_start;
	}
	public static void setChanging_background_start(boolean changing_background_start) {
		Single_State.changing_background_start = changing_background_start;
	}
	public static boolean isChanging_background_end() {
		return changing_background_end;
	}
	public static void setChanging_background_end(boolean changing_background_end) {
		Single_State.changing_background_end = changing_background_end;
	}
	public static boolean isChanging_background_allend() {
		return changing_background_allend;
	}
	public static void setChanging_background_allend(boolean changing_background_allend) {
		Single_State.changing_background_allend = changing_background_allend;
	}
	public static boolean isCyclone_happen() {
		return cyclone_happen;
	}
	public static void setCyclone_happen(boolean cyclone_happen) {
		Single_State.cyclone_happen = cyclone_happen;
	}

	
	
	public static boolean isAnimation_FlyOutOfTheDorm_running() {
		return Animation_FlyOutOfTheDorm_running;
	}
	public static void setAnimation_FlyOutOfTheDorm_running(boolean animation_FlyOutOfTheDorm_running) {
		Animation_FlyOutOfTheDorm_running = animation_FlyOutOfTheDorm_running;
	}
	private Single_State(){};
    public static Single_State getInstance(){
        if(instance==null){
               instance=new Single_State();
        }
        return instance;
    }
    

}
