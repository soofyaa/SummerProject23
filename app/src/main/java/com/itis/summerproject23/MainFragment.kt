//package com.itis.summerproject23
//
//import android.os.Bundle
//import android.view.View
//import androidx.fragment.app.Fragment
//import androidx.navigation.fragment.findNavController
//import com.bumptech.glide.Glide
//import com.itis.summerproject23.databinding.FragmentMainBinding
//
//class MainFragment : Fragment(R.layout.fragment_main) {
//
//    private var _binding: FragmentMainBinding?= null
//    private val binding get() = _binding?: throw IllegalStateException("Binding is not initialized")
//    private var adapter: RecipeAdapter? = null
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        val bundle = Bundle()
//        _binding = FragmentMainBinding.bind(view)
//        adapter = RecipeAdapter(
//            RecipeRepository.list,
//            Glide.with(this)
//        ){ recipe ->
//            bundle.putInt("ID", recipe.id)
//            //findNavController().navigate(R.id.action_mainFragment_self, bundle)
//        }
//        binding.rvRecipe.adapter = adapter
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//}