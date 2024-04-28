package com.example.timetablevfstr1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EmptyRoomsAdapter extends RecyclerView.Adapter<EmptyRoomsAdapter.ViewHolder> {

    private List<String> emptyRoomsList;
    private Context context;

    public EmptyRoomsAdapter(List<String> emptyRoomsList, Context context) {
        this.emptyRoomsList = emptyRoomsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_empty_room, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String emptyRoom = emptyRoomsList.get(position);
        holder.textViewEmptyRoom.setText(emptyRoom);
    }

    @Override
    public int getItemCount() {
        return emptyRoomsList.size();
    }

    public void updateData(List<String> emptyRooms) {
        emptyRoomsList = emptyRooms;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewEmptyRoom;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewEmptyRoom = itemView.findViewById(R.id.text_view_empty_room);
        }
    }
}