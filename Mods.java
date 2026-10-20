package dev.pulse.extra;
import java.util.*;
public class Mods {
  public static class Setting { public String n; public float min, max, v; Setting(String n,float a,float b,float v){this.n=n;min=a;max=b;this.v=v;} }
  public static class Mod {
    public String n, cat; public boolean on; public float anim; public List<Setting> s = new ArrayList<>();
    Mod(String n,String c){this.n=n;cat=c;}
    Mod set(String n,float a,float b,float v){s.add(new Setting(n,a,b,v));return this;}
    public float g(int i){return s.get(i).v;}
  }
  public static final Mod SWING = new Mod("Swing Animation","Visuals").set("Speed",0.3f,3f,1.6f);
  public static final Mod VIEW  = new Mod("View Model","Visuals").set("X",-1f,1f,0f).set("Y",-1f,1f,0f).set("Z",-1.5f,1f,0f).set("Scale",0.2f,2f,1f).set("Rotate X",-180f,180f,0f).set("Rotate Y",-180f,180f,0f).set("Rotate Z",-180f,180f,0f);
  public static final Mod FULL  = new Mod("Fullbright","Visuals");
  public static final Mod FIRE  = new Mod("Low Fire","Visuals").set("Lower",0f,0.6f,0.3f);
  public static final Mod HURT  = new Mod("No Hurt Cam","Visuals");
  public static final Mod ZOOM  = new Mod("Zoom (hold C)","Visuals").set("Factor",1.5f,8f,3f);
  public static final Mod SPRINT= new Mod("Auto Sprint","Player");
  public static final List<Mod> ALL = List.of(SWING,VIEW,FULL,FIRE,HURT,ZOOM,SPRINT);
  public static float zoomCur = 1f;
}
