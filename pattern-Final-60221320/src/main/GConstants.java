package main;

import global.LocaleData;
import shapes.GBigStar;
import shapes.GBottom;
import shapes.GDiamond;
import shapes.GHexagon;
import shapes.GLeft;
import shapes.GLight;
import shapes.GLine;
import shapes.GMultiply;
import shapes.GOval;
import shapes.GPentagon;
import shapes.GPlus;
import shapes.GPolygon;
import shapes.GRectangle;
import shapes.GRight;
import shapes.GSelect;
import shapes.GShape;
import shapes.GSmallStar;
import shapes.GStar;
import shapes.GTalkBox;
import shapes.GTop;
import shapes.GTriangle1;
import shapes.GTriangle2;


public class GConstants {
	public class CMainFrame{
		static final int x = 200;
		static final int y = 100;
		static final int w = 1100;
		static final int h = 650;
	}
	
	public enum EUserAction{
		e2Point,
		eNPoint
	}

	public enum EAnchors{
		NW,
		NN,
		NE,
		EE,
		SE,
		SS,
		SW,
		WW,
		RR,
		MM
	}
	
	public enum EShape{
		eSelect(LocaleData.GToolBar.ESELECT, new GSelect(), EUserAction.e2Point),
		ePolygon(LocaleData.GToolBar.EPOLYGON, new GPolygon(), EUserAction.eNPoint),
		eRactnagle(LocaleData.GToolBar.ERACTANGLE, new GRectangle(), EUserAction.e2Point),	
		eOval(LocaleData.GToolBar.EOVAL, new GOval(), EUserAction.e2Point),
		eLine(LocaleData.GToolBar.ELINE, new GLine(), EUserAction.e2Point),
		//
		eHexagon(LocaleData.GToolBar.EHEXAGON, new GHexagon(), EUserAction.e2Point),
		eTriangle1(LocaleData.GToolBar.ETRIANGLE1, new GTriangle1(), EUserAction.e2Point),
		eTriangle2(LocaleData.GToolBar.ETRIANGLE2, new GTriangle2(), EUserAction.e2Point),
		eDiamond(LocaleData.GToolBar.EDIAMOND, new GDiamond(), EUserAction.e2Point),
		ePentagon(LocaleData.GToolBar.EPENTAGON, new GPentagon(), EUserAction.e2Point),
		eRight(LocaleData.GToolBar.ERIGHT, new GRight(), EUserAction.e2Point),
		eLeft(LocaleData.GToolBar.ELEFT, new GLeft(), EUserAction.e2Point),
		eTop(LocaleData.GToolBar.ETOP, new GTop(), EUserAction.e2Point),
		eBottom(LocaleData.GToolBar.EBOTTOM, new GBottom(), EUserAction.e2Point),
		eSmallStar(LocaleData.GToolBar.ESMALLSTAR, new GSmallStar(), EUserAction.e2Point),
		eStar(LocaleData.GToolBar.ESTAR, new GStar(), EUserAction.e2Point),
		eBigStar(LocaleData.GToolBar.EBIGSTAR, new GBigStar(), EUserAction.e2Point),
		eTalkBox(LocaleData.GToolBar.ETALKBOX, new GTalkBox(), EUserAction.e2Point),
		eLight(LocaleData.GToolBar.ELIGHT, new GLight(), EUserAction.e2Point),
		ePlus(LocaleData.GToolBar.EPLUS, new GPlus(), EUserAction.e2Point),
		eMultiply(LocaleData.GToolBar.EMULTIPLY, new GMultiply(), EUserAction.e2Point);
		
		
		private String name;
		private GShape gSahpe;
		private EUserAction eUserAction;
		
		private EShape(String name, GShape gSahpe, EUserAction eUserAction) {
			this.name = name;
			this.gSahpe = gSahpe;
			this.eUserAction = eUserAction;
		}
		
		public String getName() {
			return this.name;
		}
		public GShape getGShape() {
			return this.gSahpe;
		}
		public EUserAction getEUserAction() {
			return this.eUserAction;
		}
	}
}
