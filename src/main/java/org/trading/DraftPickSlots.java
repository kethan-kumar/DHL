/* @Author: Yash Jaiswal */

package org.trading;

import org.trading.interfaces.IDraftPickSlots;

import java.util.HashMap;
import java.util.Map;

public class DraftPickSlots implements IDraftPickSlots {

    Map<String, Integer> draftPickSlots = new HashMap<>();

    public Map<String, Integer> getDraftPickSlots() {
        return draftPickSlots;
    }

    public void setDraftPickSlots(Map<String, Integer> draftPickSlots) {
        this.draftPickSlots = draftPickSlots;
    }

}
