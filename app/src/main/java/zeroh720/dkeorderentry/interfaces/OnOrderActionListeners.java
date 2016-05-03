package zeroh720.dkeorderentry.interfaces;

import zeroh720.dkeorderentry.model.TransactionItem;

public interface OnOrderActionListeners {
    void onConfirmAddOrderPressed(String productCode, long quantity);
    void onScanPressed();
}
