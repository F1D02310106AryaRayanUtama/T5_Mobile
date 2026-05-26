package com.example.loginpasienapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.loginpasienapp.R
import com.example.loginpasienapp.model.Pasien

class PasienAdapter : RecyclerView.Adapter<PasienAdapter.PatientViewHolder>() {

    private val patientList = mutableListOf<Pasien>()

    // Memperbarui dataset pada adapter
    fun updateList(newList: List<Pasien>) {
        patientList.clear()
        patientList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.item_pasien, parent, false)
        return PatientViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        val currentItem = patientList[position]
        holder.render(currentItem)
    }

    override fun getItemCount(): Int = patientList.size

    class PatientViewHolder(container: View) : RecyclerView.ViewHolder(container) {
        private val txtInitial: TextView = container.findViewById(R.id.tvInisial)
        private val txtName: TextView = container.findViewById(R.id.tvNama)
        private val txtBirthDate: TextView = container.findViewById(R.id.tvTanggalLahir)
        private val txtGender: TextView = container.findViewById(R.id.tvJenisKelamin)
        private val txtAddress: TextView = container.findViewById(R.id.tvAlamat)
        private val txtPhone: TextView = container.findViewById(R.id.tvNoTelepon)

        fun render(data: Pasien) {
            // Menampilkan huruf awal sebagai representasi visual
            txtInitial.text = data.nama.firstOrNull()?.uppercase() ?: "-"

            txtName.text = data.nama
            txtBirthDate.text = "Tgl Lahir  : ${data.tanggal_lahir}"
            txtGender.text = "Kelamin    : ${if (data.jenis_kelamin == "L") "Laki-laki" else "Perempuan"}"
            txtAddress.text = "Alamat     : ${data.alamat}"
            txtPhone.text = "No. Telp   : ${data.no_telepon}"
        }
    }
}
