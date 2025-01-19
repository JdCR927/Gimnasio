package es.etg.dam.pmdmJdCR.gym.data.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import es.etg.dam.pmdmJdCR.gym.data.recview.RecyclerViewAdapter
import es.etg.dam.pmdmJdCR.gym.databinding.FragmentRecyclerViewBinding

class RecyclerViewFragment : Fragment() {

    private var _binding: FragmentRecyclerViewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecyclerViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataList = listOf("Entrenamiento 1", "Entrenamiento 2", "Entrenamiento 3", "Entrenamiento 4", "Entrenamiento 5", "Entrenamiento 6")

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = RecyclerViewAdapter(dataList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}