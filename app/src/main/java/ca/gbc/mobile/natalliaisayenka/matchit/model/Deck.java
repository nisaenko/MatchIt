/**************************************************
 * Natallia Isayenka
 * 100744884
 * created: 29-Sep-2014
 * lastEdit: 5-Oct-2014
 **************************************************/

package ca.gbc.mobile.natalliaisayenka.matchit.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import ca.gbc.mobile.natalliaisayenka.matchit.R;
import ca.gbc.mobile.natalliaisayenka.matchit.widget.CardView;

/**
 * Created by nisaenko on 2014-09-26.
 */
public class Deck {

    private Card[] cards = new Card[12];
    private Integer[][] img_mc = new Integer[12][2];
    private Integer[][] img_res = new Integer[25][2];

    private CardView[] cardViews = new CardView[12];

    private ArrayList<Pair> pairs = new ArrayList<Pair>();


    public Card getCardByID(int id){
        return cards[id];
    }

    public ArrayList<Pair> getPairs() {
        return pairs;
    }

    public void setResourceID(int id, int resource){
        if (cards[id] == null) {
            cards[id] = new Card();
        }
        cards[id].setImage(resource);
    }

    public int getResourceByID(int id){
        return cards[id].getImage();
    }

    public void sedImgResource(int x, int y, Integer resource){
        img_mc[x][y] = resource;
    }

    public Integer gedImgResourceByID(int x, int y){
        return img_mc[x][y];
    }

    public void setCardViewItem(int i, CardView cardView){
        cardViews[i]= cardView;
    }

    public CardView getCardViewByID(int i){
        return cardViews[i];
    }

    public CardView[] getCardViews(){
        return cardViews;
    }

    public void shuffle(){

        //shuffle collection of 12 different images
        Collections.shuffle(Arrays.asList(img_res));

        //take and set first 6 pairs of randomly shuffled images

        for(int i = 0; i<6; i++){
            sedImgResource(i,0,img_res[i][0]);
            sedImgResource(i,1,img_res[i][1]);

            sedImgResource(i+6,0,img_res[i][0]);
            sedImgResource(i+6,1,img_res[i][1]);
        }
        Collections.shuffle(Arrays.asList(img_mc));
    }

    public void init(){
        // fill arrays with android resources
        setResourceID(0,R.id.view);
        setResourceID(1,R.id.view2);
        setResourceID(2,R.id.view3);
        setResourceID(3,R.id.view4);
        setResourceID(4,R.id.view5);
        setResourceID(5,R.id.view6);
        setResourceID(6,R.id.view7);
        setResourceID(7,R.id.view8);
        setResourceID(8,R.id.view9);
        setResourceID(9,R.id.view10);
        setResourceID(10,R.id.view11);
        setResourceID(11,R.id.view12);

        img_res[0][0]=R.drawable.back1;
        img_res[0][1]=R.drawable.ic_img1;
        img_res[1][0]=R.drawable.back2;
        img_res[1][1]=R.drawable.ic_img2;
        img_res[2][0]=R.drawable.back3;
        img_res[2][1]=R.drawable.ic_img3;
        img_res[3][0]=R.drawable.back4;
        img_res[3][1]=R.drawable.ic_img4;
        img_res[4][0]=R.drawable.back5;
        img_res[4][1]=R.drawable.ic_img5;
        img_res[5][0]=R.drawable.back6;
        img_res[5][1]=R.drawable.ic_img6;
        img_res[6][0]=R.drawable.back7;
        img_res[6][1]=R.drawable.ic_img7;
        img_res[7][0]=R.drawable.back8;
        img_res[7][1]=R.drawable.ic_img8;
        img_res[8][0]=R.drawable.back9;
        img_res[8][1]=R.drawable.ic_img9;
        img_res[9][0]=R.drawable.back10;
        img_res[9][1]=R.drawable.ic_img10;
        img_res[10][0]=R.drawable.back11;
        img_res[10][1]=R.drawable.ic_img11;
        img_res[11][0]=R.drawable.back12;
        img_res[11][1]=R.drawable.ic_img12;
        img_res[12][0]=R.drawable.back13;
        img_res[12][1]=R.drawable.ic_img13;
        img_res[13][0]=R.drawable.back14;
        img_res[13][1]=R.drawable.ic_img14;
        img_res[14][0]=R.drawable.back15;
        img_res[14][1]=R.drawable.ic_img15;
        img_res[15][0]=R.drawable.back16;
        img_res[15][1]=R.drawable.ic_img16;
        img_res[16][0]=R.drawable.back17;
        img_res[16][1]=R.drawable.ic_img17;
        img_res[17][0]=R.drawable.back18;
        img_res[17][1]=R.drawable.ic_img18;
        img_res[18][0]=R.drawable.back19;
        img_res[18][1]=R.drawable.ic_img19;
        img_res[19][0]=R.drawable.back20;
        img_res[19][1]=R.drawable.ic_img20;
        img_res[20][0]=R.drawable.back21;
        img_res[20][1]=R.drawable.ic_img21;
        img_res[21][0]=R.drawable.back22;
        img_res[21][1]=R.drawable.ic_img22;
        img_res[22][0]=R.drawable.back23;
        img_res[22][1]=R.drawable.ic_img23;
        img_res[23][0]=R.drawable.back24;
        img_res[23][1]=R.drawable.ic_img24;
        img_res[24][0]=R.drawable.back25;
        img_res[24][1]=R.drawable.ic_img25;

    }
}
