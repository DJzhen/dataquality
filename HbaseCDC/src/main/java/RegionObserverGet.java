//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import domain.DeleteEvent;
//import org.apache.hadoop.hbase.Cell;
//import org.apache.hadoop.hbase.TableName;
//import org.apache.hadoop.hbase.client.Delete;
//import org.apache.hadoop.hbase.client.Durability;
//import org.apache.hadoop.hbase.client.Put;
//import org.apache.hadoop.hbase.coprocessor.BaseRegionObserver;
//import org.apache.hadoop.hbase.coprocessor.ObserverContext;
//import org.apache.hadoop.hbase.coprocessor.RegionCoprocessorEnvironment;
//import org.apache.hadoop.hbase.filter.ByteArrayComparable;
//import org.apache.hadoop.hbase.filter.CompareFilter;
//import org.apache.hadoop.hbase.regionserver.wal.WALEdit;
//import org.apache.hadoop.hbase.util.Bytes;
//
//import java.io.IOException;
//import java.io.OutputStream;
//import java.net.Socket;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.NavigableMap;
//
///**
// * @author wang zhen
// * @version 2018/12/18
// */
//public class RegionObserverGet extends BaseRegionObserver {
//    OutputStream outputStream;
//    Socket socket;
//    String host = "192.168.100.34";
//
//    @Override
//    public void postPut(ObserverContext<RegionCoprocessorEnvironment> e, Put put,
//                        WALEdit edit, Durability durability) throws IOException {
//        putMethod(e, put);
//    }
//
//    @Override
//    public boolean postCheckAndPut(ObserverContext<RegionCoprocessorEnvironment> e, byte[] row,
//                                   byte[] family, byte[] qualifier, CompareFilter.CompareOp compareOp,
//                                   ByteArrayComparable comparator, Put put, boolean result) throws IOException {
//        boolean flag = super.postCheckAndPut(e, row, family, qualifier, compareOp, comparator, put, result);
//        if (flag) {
//            //参照postPut
//            //table.put(put);
//            putMethod(e, put);
//
//        }
//        return flag;
//    }
//
//    public void putMethod(ObserverContext<RegionCoprocessorEnvironment> e, Put put) throws IOException {
//        socket = new Socket(host, 8888);
//        outputStream = socket.getOutputStream();
//        TableName tableName = e.getEnvironment().getRegionInfo().getTable();
//        String name = tableName.getNameAsString();
//        String namespace = tableName.getNamespaceAsString();
//        byte[] row1 = put.getRow();
//        String rowkey = Bytes.toString(row1);
//        List<String> families = new ArrayList<>();
//        NavigableMap<byte[], List<Cell>> map = put.getFamilyCellMap();
//        for (byte[] key : map.keySet()) {
//            List<Cell> cells = map.get(key);
//            for (Cell cell : cells) {
//                String family1 = Bytes.toString(cell.getFamilyArray(), cell.getFamilyOffset(), cell.getFamilyLength());
//                families.add(family1);
//                String column = Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength());
//                String value = Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
//                String timeStamp = String.valueOf(cell.getTimestamp());
//                DeleteEvent bean = new DeleteEvent(namespace, name, rowkey, families, column, value, timeStamp, "put");
//                String jsonString = JSONObject.toJSONString(bean);
//                outputStream.write(Bytes.toBytes(jsonString));
//                outputStream.write("\n".getBytes());
//                outputStream.flush();
//            }
//        }
//        outputStream.close();
//        socket.close();
//    }
//
//    @Override
//    public void preDelete(ObserverContext<RegionCoprocessorEnvironment> ctx, Delete delete, WALEdit edit, Durability durability) throws IOException {
//        socket = new Socket(host, 8888);
//        outputStream = socket.getOutputStream();
//        TableName tableName = ctx.getEnvironment().getRegionInfo().getTable();
//        String namespace = tableName.getNamespaceAsString();
//        DeleteEvent deleteEvent;
//        String rowkey = Bytes.toString(delete.getRow());
//        List<String> families = new ArrayList<>();
//        String family = null;
//        String qualifier = null;
//        String value = null;
//        String timestampStr = null;
//        String typeStr = null;
//        int numFamilies = delete.numFamilies();
//        NavigableMap<byte[], List<Cell>> familyCellMap = delete.getFamilyCellMap();
//        for (Map.Entry<byte[], List<Cell>> entry : familyCellMap.entrySet()) {
//            List<Cell> cells = entry.getValue();
//            for (Cell cell : cells) {
//                byte[] familyArray = cell.getFamilyArray();
//                if (familyArray != null) {
//                    family = Bytes.toString(familyArray, cell.getFamilyOffset(), cell.getFamilyLength());
//                    families.add(family);
//                }
//                byte[] qualifierArray = cell.getQualifierArray();
//                if (qualifierArray != null)
//                    qualifier = Bytes.toString(qualifierArray, cell.getQualifierOffset(), cell.getQualifierLength());
//                byte[] valueArray = cell.getValueArray();
//                if (valueArray != null)
//                    value = Bytes.toString(valueArray, cell.getValueOffset(), cell.getValueLength());
//                long timestamp = cell.getTimestamp();
//                if (timestamp == Long.MAX_VALUE) {
//                    timestampStr = "LATEST_TIMESTAP";
//                } else {
//                    timestampStr = String.valueOf(timestamp);
//                }
//                byte type = cell.getTypeByte();
//                switch (type) {
//                    case (byte) 8:
//                        typeStr = "Delete";
//                        break;
//                    case (byte) 10:
//                        typeStr = "DeleteFamilyVersion";
//                        break;
//                    case (byte) 12:
//                        typeStr = "DeleteColumn";
//                        break;
//                    case (byte) 14:
//                        typeStr = "DeleteFamily";
//                        break;
//                    default:
//                        typeStr = "-";
//                        break;
//                }
//            }
//        }
//
//        deleteEvent = new DeleteEvent(namespace, tableName.toString(), rowkey, families, qualifier, value, timestampStr, typeStr);
//        String deleteEventStr = JSON.toJSONString(deleteEvent);
//        outputStream.write(deleteEventStr.getBytes());
//        outputStream.write("\n".getBytes());
//        outputStream.flush();
//        outputStream.close();
//        socket.close();
//    }
//}
