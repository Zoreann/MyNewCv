import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mynewcv.R

class ProjectAdapter : RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder>() {

    private val projectList = mutableListOf<String>()

    fun setData(data: List<String>) {
        projectList.clear()
        projectList.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_project, parent, false)
        return ProjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        val projectName = projectList[position]
        holder.bind(projectName)
    }

    override fun getItemCount(): Int {
        return projectList.size
    }

    inner class ProjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val projectNameTextView: TextView = itemView.findViewById(R.id.projectNameTextView)

        fun bind(projectName: String) {
            projectNameTextView.text = projectName
        }
    }
}
