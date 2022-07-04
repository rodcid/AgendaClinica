package com.example.fichasclinicas.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.Spinner
import android.widget.TextView
import com.example.fichasclinicas.R
import com.example.fichasclinicas.models.Agenda

class AgendaAdapter(val ctx: Context, private val agenda: List<Agenda>):BaseAdapter() {
    override fun getCount(): Int {
        return agenda.size
    }

    override fun getItem(i: Int): Agenda {
        return agenda[i]
    }

    override fun getItemId(i: Int): Long {
        return agenda[i].id!!
    }

    override fun getView(i: Int, view: View?, viewgroup: ViewGroup?): View {
        val inflater = LayoutInflater.from(ctx)
        val rowView = inflater.inflate(R.layout.agenda_item, null)
        val agenda = agenda[i]

        val tvHora = rowView.findViewById<TextView>(R.id.agenda_item_tv_hora)
        val tvPaciente = rowView.findViewById<TextView>(R.id.agenda_item_tv_paciente)
        val tvMedico = rowView.findViewById<TextView>(R.id.agenda_item_tv_medico)
        val tvExamen = rowView.findViewById<TextView>(R.id.agenda_item_tv_examen)
        val tvObservacion = rowView.findViewById<TextView>(R.id.agenda_item_tv_observacion)
        /*
        val spnStatus = rowView.findViewById<Spinner>(R.id.agenda_item_spn_state)

        val adapter = ArrayAdapter.createFromResource(
            ctx,
            R.array.status_array,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnStatus.adapter = adapter

         */

        tvHora.text = agenda.hora
        tvPaciente.text = agenda.paciente
        tvMedico.text = agenda.medico
        tvExamen.text = agenda.examen
        tvObservacion.text = agenda.observacion


        return rowView
    }


}