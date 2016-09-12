package brain.birthday;

import android.support.v7.widget.RecyclerView;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Саня on 18.08.2016.
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    ItemDAO itemDAO;
    Item item = new Item();
    Date date = new Date();
   // Calendar.t
    private List<Item> contactList;
    public ContactAdapter(List<Item> contactList, DBHelper2 dbHelper) {
        this.contactList = contactList;
        itemDAO = new ItemDAO(dbHelper);
    }


    @Override
    public int getItemCount() {
        return contactList.size();
    }

    @Override
    public void onBindViewHolder(ContactViewHolder contactViewHolder, int i) {

        Item ci = contactList.get(i);
        Long date3 =Long.valueOf(ci.date);
        String date5 = new SimpleDateFormat("dd.MM.yyyy").format(new Date(date3));
        //Log.d("sssssssss","sss"+ date5);
        date.getTime();
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        Date date = new Date();
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
        Calendar calendar = new GregorianCalendar();
        String dd = date5.substring(0, 2);
        String MM = date5.substring(3, 5);
        String yyyy = date5.substring(6, 10);
        Log.d("sssssssss"," yyyy"+ yyyy + " MM " + MM + " dd " + dd);
        Calendar calendar2 = new GregorianCalendar(calendar.get(Calendar.YEAR),Integer.valueOf(MM)-1,Integer.valueOf(dd));
        long days;
        long difference;
        if(calendar2.getTimeInMillis()>calendar.getTimeInMillis()) {
             difference = calendar2.getTimeInMillis() - calendar.getTimeInMillis();

        }else{
            calendar2 = new GregorianCalendar(calendar.get(Calendar.YEAR) + 1,Integer.valueOf(MM)-1,Integer.valueOf(dd));
            difference =  calendar2.getTimeInMillis() - calendar.getTimeInMillis() ;
        }
         days = difference/(24*60*60*1000);

        contactViewHolder.vName.setText(ci.name);
        contactViewHolder.vDate.setText(date5);
        contactViewHolder.vNumber.setText("" + days );

    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_view, viewGroup, false);


        return new ContactViewHolder(itemView);
    }
    public void swap(int firstPosition, int secondPosition){
        Collections.swap(contactList, firstPosition, secondPosition);
        notifyItemMoved(firstPosition, secondPosition);
    }

    public void remove(int position) {
        Item item = contactList.get(position);
        itemDAO.delete(item);
        contactList.remove(position);
        notifyItemRemoved(position);
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {

        protected TextView vName;
        protected TextView vDate;
        protected TextView vNumber;

        public ContactViewHolder(View v) {
            super(v);
            vName = (TextView) v.findViewById(R.id.textView);
            vDate = (TextView) v.findViewById(R.id.textView2);
            vNumber = (TextView) v.findViewById(R.id.textView3);
        }

    }


}
