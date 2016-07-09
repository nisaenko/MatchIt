/**************************************************
 * Natallia Isayenka
 * 100744884
 * created: 29-Sep-2014
 * lastEdit: 5-Oct-2014
 **************************************************/


package ca.gbc.mobile.natalliaisayenka.matchit.widget;

import android.widget.ImageView;
import android.content.Context;
import android.util.AttributeSet;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import ca.gbc.mobile.natalliaisayenka.matchit.model.Card;

public class CardView extends ImageView {

    private Card card;

    public CardView(Context context) {
        super(context);

    }

    public CardView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public CardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setFocusable(true);

    }

    public CardView(Context context, Card card) {
        super(context);
        this.card=card;
    }


    public CardView(Context context, AttributeSet attrs, int defStyle, Card card) {
        super(context, attrs, defStyle);
        setFocusable(true);
        this.card=card;
    }

    @Override
    protected boolean onSetAlpha(int alpha) {
        return false;
    }
    @Override
    public void onInitializeAccessibilityEvent(AccessibilityEvent event) {
        super.onInitializeAccessibilityEvent(event);
        event.setClassName(CardView.class.getName());
    }
    @Override
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
        super.onInitializeAccessibilityNodeInfo(info);
        info.setClassName(CardView.class.getName());
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }


}
