package com.example.movienight.viewpager.tab1recyclerview

import Movie
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movienight.databinding.FragmentTab1RecyclerViewBinding

class Tab1RecyclerViewFragment : Fragment() {

    private lateinit var binding: FragmentTab1RecyclerViewBinding
    private lateinit var moviePopularAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTab1RecyclerViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val empty_movieList_for_Testing = mutableListOf<Movie>()



        empty_movieList_for_Testing.apply {
            empty_movieList_for_Testing.apply {
                add(Movie("poster1.jpg", "Star Wars", 8.2, "A movie about a war in a galaxy far, far away"))
                add(Movie("poster2.jpg", "The Shawshank Redemption", 9.3, "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency."))
                add(Movie("poster3.jpg", "The Godfather", 9.2, "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son."))
                add(Movie("poster4.jpg", "The Dark Knight", 9.0, "When the menace known as The Joker emerges from his mysterious past, he wreaks havoc and chaos on the people of Gotham."))
                add(Movie("poster5.jpg", "Pulp Fiction", 8.9, "The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption."))
                add(Movie("poster6.jpg", "Fight Club", 8.8, "An insomniac office worker and a devil-may-care soapmaker form an underground fight club that evolves into something much, much more."))
                add(Movie("poster7.jpg", "Forrest Gump", 8.8, "The presidencies of Kennedy and Johnson, the events of Vietnam, Watergate, and other historical events unfold from the perspective of an Alabama man with an IQ of 75, whose only desire is to be reunited with his childhood sweetheart."))
                add(Movie("poster8.jpg", "Inception", 8.7, "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O."))
                add(Movie("poster9.jpg", "The Matrix", 8.7, "A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers."))
                add(Movie("poster10.jpg", "Goodfellas", 8.7, "The story of Henry Hill and his life in the mob, covering his relationship with his wife Karen Hill and his mob partners Jimmy Conway and Tommy DeVito in the Italian-American crime syndicate."))
                add(Movie("poster11.jpg", "The Silence of the Lambs", 8.6, "A young F.B.I. cadet must receive the help of an incarcerated and manipulative cannibal killer to help catch another serial killer, a madman who skins his victims."))
                add(Movie("poster12.jpg", "Saving Private Ryan", 8.6, "Following the Normandy Landings, a group of U.S. soldiers go behind enemy lines to retrieve a paratrooper whose brothers have been killed in action."))
                add(Movie("poster13.jpg", "The Green Mile", 8.6, "The lives of guards on Death Row are affected by one of their charges: a black man accused of child murder and rape, yet who has a mysterious gift."))
                add(Movie("poster14.jpg", "The Usual Suspects", 8.5, "A sole survivor tells of the twisty events leading up to a horrific gun battle on a boat, which began when five criminals met at a seemingly random police lineup."))
                add(Movie("poster15.jpg", "Se7en", 8.6, "Two detectives, a rookie and a veteran, hunt a serial killer who uses the seven deadly sins as his motives."))
                add(Movie("poster16.jpg", "Léon: The Professional", 8.5, "Mathilda, a 12-year-old girl, is reluctantly taken in by Léon, a professional assassin, after her family is murdered. Léon and Mathilda form an unusual relationship, as she becomes his protégée and learns the assassin's trade."))
                add(Movie("poster17.jpg", "The Lion King", 8.5, "Lion cub and future king Simba searches for his identity. His eagerness to please others and penchant for testing his boundaries sometimes gets him into trouble."))
                add(Movie("poster18.jpg", "Back to the Future", 8.5, "Marty McFly, a 17-year-old high school student, is accidentally sent thirty years into the past in a time-traveling DeLorean invented by his close friend, eccentric scientist Doc Brown."))
                add(Movie("poster19.jpg", "Gladiator", 8.5, "A former Roman General sets out to exact vengeance against the corrupt emperor who murdered his family and sent him into slavery."))
                add(Movie("poster20.jpg", "The Prestige", 8.5, "After a tragic accident, two stage magicians engage in a battle to create the ultimate illusion while sacrificing everything they have to outwit each other."))
                add(Movie("poster21.jpg", "The Departed", 8.5, "An undercover cop and a mole in the police attempt to identify each other while infiltrating an Irish gang in Boston."))
                add(Movie("poster22.jpg", "The Intouchables", 8.5, "After he becomes a quadriplegic from a paragliding accident, an aristocrat hires a young man from the projects to be his caregiver."))
                add(Movie("poster23.jpg", "Whiplash", 8.5, "A promising young drummer enrolls at a cut-throat music conservatory where his dreams of greatness are mentored by an instructor who will stop at nothing to realize a student's potential."))
                add(Movie("poster24.jpg", "The Pianist", 8.5, "A Polish Jewish musician struggles to survive the destruction of the Warsaw ghetto of World War II."))
                add(Movie("poster25.jpg", "American History X", 8.5, "A former neo-nazi skinhead tries to prevent his younger brother from going down the same wrong path that he did."))
                add(Movie("poster26.jpg", "The Usual Suspects", 8.5, "A sole survivor tells of the twisty events leading up to a horrific gun battle on a boat, which began when five criminals met at a seemingly random police lineup."))
                add(Movie("poster27.jpg", "The Shining", 8.4, "A family heads to an isolated hotel for the winter where a sinister presence influences the father into violence, while his psychic son sees horrific forebodings from both past and future."))
                add(Movie("poster28.jpg", "The Sixth Sense", 8.1, "A boy who communicates with spirits seeks the help of a disheartened child psychologist."))
                add(Movie("poster29.jpg", "Joker", 8.5, "In Gotham City, mentally troubled comedian Arthur Fleck is disregarded and mistreated by society. He then embarks on a downward spiral of revolution and bloody crime. This path brings him face-to-face with his alter-ego: the Joker."))
                add(Movie("poster30.jpg", "Interstellar", 8.6, "A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival."))
            }
        }

        moviePopularAdapter = MovieAdapter( requireContext() ,empty_movieList_for_Testing)

        binding.tab1RecyclerView.adapter = moviePopularAdapter
        binding.tab1RecyclerView.layoutManager = LinearLayoutManager(requireContext())

    }
}
