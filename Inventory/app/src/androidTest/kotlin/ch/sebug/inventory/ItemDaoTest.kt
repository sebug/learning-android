package ch.sebug.inventory

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import ch.sebug.inventory.data.InventoryDatabase
import ch.sebug.inventory.data.Item
import ch.sebug.inventory.data.ItemDao
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class ItemDaoTest {
    private lateinit var itemDao: ItemDao
    private lateinit var inventoryDatabase: InventoryDatabase

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        inventoryDatabase = Room.inMemoryDatabaseBuilder(context,
            InventoryDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        itemDao = inventoryDatabase.itemDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        inventoryDatabase.close()
    }

    private var item1 = Item(1, "Apples", 10.0, 20)
    private var item2 = Item(2, "Bananas", 15.0, 97)

    private suspend fun addOneItemToDb() {
        itemDao.insert(item1)
    }

    private suspend fun addTwoItemsToDb() {
        itemDao.insert(item1)
        itemDao.insert(item2)
    }

    @Test
    @Throws(IOException::class)
    fun daoInsert_insertsItemIntoDB() = runBlocking {
        addOneItemToDb()
        val allItems = itemDao.getAll().first()
        assertEquals(allItems[0], item1)
    }

    @Test
    @Throws(IOException::class)
    fun daoGetAllItems_returnsAllItemsFromDB() = runBlocking {
        addTwoItemsToDb()
        val allItems = itemDao.getAll().first()
        assertEquals(2, allItems.size)
    }

    @Test
    @Throws(IOException::class)
    fun daoUpdateItems_updatesItemsInDB() = runBlocking {
        addTwoItemsToDb()
        val allItems = itemDao.getAll().first()
        val item = allItems.last()
        val quantityBefore = item.quantity
        val quantityAfter = quantityBefore - 1
        val itemId = item.id
        val newItem = item.copy(quantity = quantityAfter)
        itemDao.update(newItem)
        val newItemAfter = itemDao.getItem(itemId).first()
        assertEquals(quantityAfter, newItemAfter.quantity)
    }

    @Test
    @Throws(IOException::class)
    fun daoDeleteItems_deletesAllItemsFromDB() = runBlocking {
        addTwoItemsToDb()
        val allItems = itemDao.getAll().first()
        allItems.forEach {
            itemDao.delete(it)
        }
        val allItemsAfter = itemDao.getAll().first()
        assertEquals(0, allItemsAfter.size)
    }
}