/* Copyright 2015 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================*/

package com.example.smartcare;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.smartcare.Classifier.Recognition;

import java.util.List;

public class RecognitionScoreView extends View implements ResultsView {
  private static final float TEXT_SIZE_DIP = 24;
  private List<Recognition> results;
  private final float textSizePx;
  private final Paint fgPaint;
  private final Paint bgPaint;
  //food list
//  private final int foodList[] = {11005,11006,11007,11008,11009,11010,11011,11012,11013,11014,11015,11016,11017,11018,11019,11020,11021,11022,11023,11024,11025,11026,11027,11028,11029,11030,11031,11032,11033,11034,11035,11036,11037,11038,11039,11040,11041,11042,11043,11044,11045,11046,11047,11048,11049,11050,11051,11052,11053,11054,11055,11056,11057,11058,11059,11060,11061,11062,11063,11064,11065,11066,11067,11068,11069,11070,11071,11072,11073,11074,11075,11076,11077,11078,11079,11080,11081,11082,11083,11084,11085,11086,11087,11088,11089,11090,11091,11092,11093,11094,11095,11096,11097,11098,11099,11100,11101,11102};
//  private final String foodName[] = {"กระเพาะปลา ปรุงสำเร็จ","ก๋วยเตี๋ยวแขก(ก๋วยเตี๋ยวแกง)","ก๋วยเตี๋ยวเนื้อสับ","ก๋วยเตี๋ยวผัดไทย ใส่ไข่","ก๋วยเตี๋ยวผัดปู รวมถั่วงอกดิบ","ก๋วยเตี๋ยวเส้นเล็กหมู หมู ตับ แห้ง","ก๋วยเตี๋ยวเส้นใหญ่ เย็นตาโฟ  น้ำ","ก๋วยเตี๋ยวเส้นใหญ่ ราดหน้ากุ้ง","ก๋วยเตี๋ยวเส้นใหญ่ ราดหน้าไก่","ก๋วยเตี่ยวเส้นใหญ่ ราดหน้าหมู","ก๋วยเตี๋ยวเส้นใหญ่ผัดซีอิ้วใส่ไข่","ก้อยอีสาน","แกงกล้วยกับไก่","แกงไก่ใส่หยวกกล้วย ภาคใต้","แกงขนุนอ่อน","แกงขี้เหล็ก","แกงเขียวหวานหมู","แกงไข่(ไข่เป็ด)","แกงไตปลา","แกงน้ำเคย(แกงเคยปลา)","แกงบอน","แกงผักกาดจอ ภาคเหนือ","แกงเผือก(ใส่ปลา)","แกงพริกปลาตัวเล็ก","แกงมัสมั่นเนื้อ","แกงส้มผักรวมกับปลา ภาคกลาง","แกงส้มผักรวมกับปลา ภาคใต้","แกงหน่อไม้ ภาคอิสาน","แกงหมูชะมวง","แกงหอยขม ภาคกลาง","แกงหอยขม ภาคอิสาน","แกงเหลืองใส่ปลา","แกงอ่อมเนื้อ","แกงอ่อมปลา","แกงฮังเล","ไก่กอและ","ขนมจีนซาวน้ำ ภาคกลาง","ขนมจีนน้ำเงี้ยว รวมผัก","ขนมจีนน้ำนัวปลาร้า รวมผักลวก","ขนมจีนน้ำพริก รวมผัก ","ขนมจีนน้ำพริก ภาคใต้ รวมผัก ","ขนมจีนน้ำยา รวมผักสด และผักกาดดอง","ขนมจีนน้ำยาปักษ์ใต้ รวมผักสด","ขนมจีนน้ำยาป่า รวมผักสด","ขนมจีนน้ำละซอ ภาคใต้","ขนมผักกาดผัด ใส่ไข่","ข้าวแกงเขียวหวานไก่","ข้าวขาหมู","ข้าวคลุกกะปิ","ข้าวซอยไก่","ข้าวผัดหมู ใส่ไข่","ข้าวมันไก่","ข้าวยำ (ข้าวสีม่วง)","ข้าวยำคลุก","ข้าวยำปักษ์ใต้","ข้าวราดหน้าไก่ผัดใบกระเพรา","ข้าวหมกไก่","ข้าวหมูแดง","ข้าวเหนียวชุบไข่ปิ้ง","ซุปกะทิหน่อไม้","ซุปมะเขือ ภาคอิสาน(ยำมะเขือเปราะ)","ซุปมะเขือยาว ภาคอิสาน","ซุปหน่อไม้ กรุงเทพ","ซุปหน่อไม้ ภาคอิสาน","ตำมะเขือพวง ภาคอิสาน","เต้าคั่ว (สลัดทะเล) ภาคใต้","เต้าเจี้ยวหลน รวมผักลวก","นาชิติเน๊ะ (ข้าวอัดเป็นแท่งราดด้วยปลานึ่งและมะพร้าวคั่ว)","นาชิดาแฆ (ข้าวเจ้า+ข้าวเหนียว หุงกับกะทิ ราดแกงกุ้ง) ภาคใต้","นาชิกาแฆ (ข้าวเจ้า+ข้าวเหนียว หุงกับกะทิ ราดแกงปลา) ภาคใต้","นาชิมีเยาะ ( ข้าวเจ้าหุงกับกะทิ ราดแกงตั๊มิ  แกงปลา","น้ำพริกปลาร้า รวมผักลวก","น้ำพริกอ่อง","ผัดคั่วกลิ้งเนื้อวัว ภาคใต้","ผัดหมี่ไทย ภาคใต้","ยำบูดู","ยำสาหร่ายผมนาง ","ลาบคั่วหมู ภาคเหนือ","ลาบเนื้อ อิสาน รวมผักสด","ลาบเลือดอิสาน","สัมตำ อิสาน","ส้มตำปลาร้า รวมผักสด","ส้มตำปู-กุ้ง","เส้นหมี่ ลูกชิ้นเนื้อวัวน้ำ","เส้นหมี่ผัดซีอิ้ว","ไส้กรอก อิสาน","ไส้อั่ว","หมี่กรอบ","หมี่กะทิ","หมี่กะทิรวมผักสด","หอยแมลงภู่ทอด","ห่อหมกไข่ปลา ภาคอิสาน","ห่อหมกปลาช่อนใบยอ","แกงอ่อมมะระใส่เห็ด อาหารเจ","ผัดวุ้นเส้น(ผัดโฮ๊ะ) อาหารเจ","ทอดมันถั่วเขียว อาหารเจ","แกงส้มผักรวม อาหารเจ","ยำก๋วยเตี๋ยวเซียงไฮ้ อาหารเจ","เกี๊ยนทอด อาหารเจ","เย็นตาโฟ อาหารเจ","หมี่เหลืองผัดซีอิ้ว อาหารเจ","หมี่กรอบ อาหารเจ"
//  };
//  private final int foodCalorie[] = {83,131,112,239,243,227,72,84,109,113,195,105,105,117,49,122,93,30,34,39,39,56,50,74,252,24,50,26,197,130,44,44,55,53,221,233,120,84,87,152,109,81,85,65,91,196,154,152,209,154,178,199,169,160,164,191,158,169,231,35,40,59,41,25,109,70,72,119,213,164,163,83,212,154,208,50,102,182,104,99,28,42,85,51,164,398,420,505,173,140,219,230,126,49,152,248,33,80,197,188,131,182
//  };

  public RecognitionScoreView(final Context context, final AttributeSet set) {
    super(context, set);

    textSizePx =
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, TEXT_SIZE_DIP, getResources().getDisplayMetrics());
    fgPaint = new Paint();
    fgPaint.setTextSize(textSizePx);

    bgPaint = new Paint();
    bgPaint.setColor(0xcc4285f4);
  }

  @Override
  public void setResults(final List<Recognition> results) {
    this.results = results;
    postInvalidate();
  }

  @Override
  public void onDraw(final Canvas canvas) {
    final int x = 10;
    int y = (int) (fgPaint.getTextSize() * 1.5f);

    canvas.drawPaint(bgPaint);

    if (results != null) {
      for (final Recognition recog : results) {
        //new codes
        //------------------------------------------------
//        //get title which is String and convert to number
//        int title = Integer.parseInt(recog.getTitle());
//        //search for title number in a food array to get food name and calorie
//        for(int i=0; i<foodList.length; i++) {
//          if(title == foodList[i]) {
//            //found
//            canvas.drawText(foodName[i] + ": " + foodCalorie[i] + " แคลอรี่", x, y, fgPaint);
//            break;
//          }
//        }
//        //show only the first result
//        break;
        //------------------------------------------------

        //old codes
//        canvas.drawText(recog.getTitle() + ": " + recog.getConfidence(), x, y, fgPaint);

        if(recog.getConfidence() > 0.50) {
          canvas.drawText(recog.getTitle(), x, y, fgPaint);
          y += fgPaint.getTextSize() * 1.5f;
        }
      }
    }
  }
}
