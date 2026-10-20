package dev.pulse.extra;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
public class ClickGui extends Screen {
  static final int W=520,H=300,SB=44,RP=170,CW=(W-SB-RP-22)/2,CH=28;
  static final String[] CATS={"Visuals","Player"}, ICON={"V","P"};
  static int cat=0; static Mods.Mod sel;
  Mods.Setting drag; int x0,y0;
  public ClickGui(){super(Text.literal("Pulse Extra"));}
  @Override public boolean shouldPause(){return false;}
  static int lerp(int a,int b,float t){int r=0;for(int s=0;s<=24;s+=8){int ca=(a>>s)&255,cb=(b>>s)&255;r|=((int)(ca+(cb-ca)*t)&255)<<s;}return r;}
  void rr(DrawContext c,int x,int y,int w,int h,int col){c.fill(x+1,y,x+w-1,y+h,col);c.fill(x,y+1,x+w,y+h-1,col);}
  int cx(int n){return x0+SB+8+(n%2)*(CW+6);} int cy(int n){return y0+30+(n/2)*(CH+6);}
  int rx(){return x0+W-RP+10;} int bw(){return RP-20;} int sy(int i){return y0+44+i*32;}
  @Override public void render(DrawContext c,int mx,int my,float d){
    x0=(width-W)/2;y0=(height-H)/2;
    c.fill(0,0,width,height,0x88000000);
    rr(c,x0-1,y0-1,W+2,H+2,0xFF2A2F55); rr(c,x0,y0,W,H,0xFF0D1020);
    rr(c,x0+4,y0+4,SB-8,H-8,0xFF11152A);
    for(int i=0;i<CATS.length;i++){int bx=x0+8,by=y0+14+i*34;rr(c,bx,by,28,28,cat==i?0xFF2B3170:0xFF181D38);c.drawCenteredTextWithShadow(textRenderer,ICON[i],bx+14,by+10,cat==i?0xFFFFFFFF:0xFF8890C0);}
    c.fill(x0+W-RP,y0+8,x0+W-RP+1,y0+H-8,0xFF232850);
    c.drawText(textRenderer,CATS[cat],x0+SB+10,y0+12,0xFF8890C0,false);
    int n=0;
    for(Mods.Mod m:Mods.ALL){ if(!m.cat.equals(CATS[cat]))continue;
      int x=cx(n),y=cy(n);n++;
      m.anim+=((m.on?1f:0f)-m.anim)*0.25f;
      rr(c,x,y,CW,CH,lerp(sel==m?0xFF3A4180:0xFF262B50,0xFF6C7BFF,m.anim));
      rr(c,x+1,y+1,CW-2,CH-2,lerp(0xFF121630,0xFF1A2050,m.anim));
      c.drawText(textRenderer,m.n,x+8,y+10,lerp(0xFF8890C0,0xFFFFFFFF,m.anim),false);
      if(!m.s.isEmpty())c.drawText(textRenderer,"...",x+CW-52,y+10,0xFF6C7BFF,false);
      rr(c,x+CW-30,y+9,22,10,lerp(0xFF2A2F50,0xFF6C7BFF,m.anim));
      rr(c,x+CW-28+(int)(m.anim*10),y+11,8,6,0xFFFFFFFF);
    }
    int rx=rx();
    if(sel==null)c.drawText(textRenderer,"Select a module",rx,y0+12,0xFF8890C0,false);
    else{
      c.drawText(textRenderer,sel.cat+" / "+sel.n,rx,y0+12,0xFFFFFFFF,false);
      if(sel.s.isEmpty())c.drawText(textRenderer,"No settings",rx,y0+44,0xFF8890C0,false);
      for(int i=0;i<sel.s.size();i++){Mods.Setting s=sel.s.get(i);int y=sy(i);
        c.drawText(textRenderer,s.n,rx,y,0xFFC8CCF0,false);
        String v=String.format("%.2f",s.v);c.drawText(textRenderer,v,rx+bw()-textRenderer.getWidth(v),y,0xFF6C7BFF,false);
        int fw=(int)(bw()*(s.v-s.min)/(s.max-s.min));
        rr(c,rx,y+14,bw(),4,0xFF232850);rr(c,rx,y+14,fw,4,0xFF6C7BFF);rr(c,rx+fw-3,y+11,6,10,0xFFFFFFFF);}
    }
    c.drawText(textRenderer,"RShift - menu",x0+10,y0+H-14,0xFF444A78,false);
  }
  void upd(double mx){ if(drag==null)return; float t=(float)Math.max(0,Math.min(1,(mx-rx())/bw())); drag.v=drag.min+t*(drag.max-drag.min); }
  @Override public boolean mouseClicked(double mx,double my,int b){
    for(int i=0;i<CATS.length;i++){int bx=x0+8,by=y0+14+i*34;if(mx>=bx&&mx<bx+28&&my>=by&&my<by+28)cat=i;}
    int n=0;
    for(Mods.Mod m:Mods.ALL){ if(!m.cat.equals(CATS[cat]))continue;
      int x=cx(n),y=cy(n);n++;
      if(mx>=x&&mx<x+CW&&my>=y&&my<y+CH){ boolean gear=mx>=x+CW-52&&mx<x+CW-34; if(!gear&&b==0)m.on=!m.on; sel=m; return true; } }
    if(sel!=null)for(int i=0;i<sel.s.size();i++){int y=sy(i);if(mx>=rx()-4&&mx<=rx()+bw()+4&&my>=y+8&&my<=y+24){drag=sel.s.get(i);upd(mx);return true;}}
    return true;
  }
  @Override public boolean mouseDragged(double mx,double my,int b,double dx,double dy){upd(mx);return true;}
  @Override public boolean mouseReleased(double mx,double my,int b){drag=null;return true;}
}
