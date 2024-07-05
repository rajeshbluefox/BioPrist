package bluefox.rajesh.medicalrepresentative.zSharedPreference

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import bluefox.rajesh.medicalrepresentative.salesModule.newOrder.modelClass.MedicineData

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "products.db"
        private const val DATABASE_VERSION = 1

        // Table and column names
        private const val TABLE_PRODUCTS = "Products"
        private const val COLUMN_PRODUCT_ID = "ProductId"
        private const val COLUMN_CUSTOMER_ID = "CustomerId"
        private const val COLUMN_MEDICINE_ID = "MedicineId"
        private const val COLUMN_MEDICINE_NAME = "MedicineName"
        private const val COLUMN_RATE = "Rate"
        private const val COLUMN_MRP = "MRP"
        private const val COLUMN_STOCK = "Stock"
        private const val COLUMN_QUANTITY = "Quantity"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_PRODUCTS (
                $COLUMN_PRODUCT_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_CUSTOMER_ID INTEGER,
                $COLUMN_MEDICINE_ID INTEGER,
                $COLUMN_MEDICINE_NAME VARCHAR(100),
                $COLUMN_RATE VARCHAR(20),
                $COLUMN_MRP VARCHAR(20),
                $COLUMN_STOCK VARCHAR(20),
                $COLUMN_QUANTITY VARCHAR(20)
            );
        """.trimIndent()
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_PRODUCTS")
        onCreate(db)
    }

    // Method to insert data
    fun insertMedicineData(medicineData: MedicineData): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(COLUMN_CUSTOMER_ID, medicineData.customerId)
            put(COLUMN_MEDICINE_ID, medicineData.medicineId)
            put(COLUMN_MEDICINE_NAME, medicineData.medicineName)
            put(COLUMN_RATE, medicineData.rate)
            put(COLUMN_MRP, medicineData.mrp)
            put(COLUMN_STOCK, medicineData.stock)
            put(COLUMN_QUANTITY, medicineData.quantity)
        }
        val result = db.insert(TABLE_PRODUCTS, null, contentValues)
        return result != -1L
    }

    // Method to fetch all rows
    fun getAllMedicines(): ArrayList<MedicineData> {
        val medicineList = ArrayList<MedicineData>()
        val db = this.readableDatabase
        val selectQuery = "SELECT * FROM $TABLE_PRODUCTS"
        val cursor: Cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            do {
                val medicine = MedicineData(
                    medicineId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_MEDICINE_ID)),
                    medicineName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MEDICINE_NAME)),
                    rate = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RATE)),
                    mrp = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MRP)),
                    stock = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STOCK)),
                    quantity = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_QUANTITY)),
                    customerId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CUSTOMER_ID))
                )
                medicineList.add(medicine)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return medicineList
    }

    // Method to fetch products by CustomerId
    fun getMedicinesByCustomerId(customerId: Int): ArrayList<MedicineData> {
        val medicineList = ArrayList<MedicineData>()
        val db = this.readableDatabase
        val selectQuery = "SELECT * FROM $TABLE_PRODUCTS WHERE $COLUMN_CUSTOMER_ID = ?"
        val cursor: Cursor = db.rawQuery(selectQuery, arrayOf(customerId.toString()))

        if (cursor.moveToFirst()) {
            do {
                val medicine = MedicineData(
                    medicineId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_MEDICINE_ID)),
                    medicineName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MEDICINE_NAME)),
                    rate = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RATE)),
                    mrp = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MRP)),
                    stock = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STOCK)),
                    quantity = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_QUANTITY)),
                    customerId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CUSTOMER_ID))
                )
                medicineList.add(medicine)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return medicineList
    }

    // Method to truncate data
    fun truncateTable() {
        val db = this.writableDatabase
        db.execSQL("DELETE FROM $TABLE_PRODUCTS")
    }
}
