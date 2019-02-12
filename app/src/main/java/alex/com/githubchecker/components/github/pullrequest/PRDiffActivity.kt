package alex.com.githubchecker.components.github.pullrequest

import alex.com.githubchecker.components.app.BaseActivity
import alex.com.githubchecker.components.app.GithubCheckerApp
import alex.com.githubchecker.models.dagger.GithubModel
import android.content.Context
import android.content.Intent
import android.os.Bundle
import butterknife.ButterKnife
import javax.inject.Inject

/**
 * Created by Alex on 11/17/2017.
 */

public class PRDiffActivity : BaseActivity() {

    companion object {

        private val KEY_PR_ID = "id"

        fun Show(context: Context, pullRequestId: Int?) {
            val intent = Intent(context, PRDiffActivity::class.java)
            intent.putExtra(KEY_PR_ID, pullRequestId)
            context.startActivity(intent)
        }
    }

    private lateinit var view: PRDiffView
    private lateinit var presenter: PRDiffPresenter

    @Inject
    lateinit var githubModel: GithubModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pullRequestId = intent.getIntExtra(KEY_PR_ID, 0)

        ButterKnife.bind(this)
        GithubCheckerApp.githubComponent.inject(this)

        view = PRDiffView(this)
        presenter = PRDiffPresenter(githubModel!!, view, pullRequestId)

        setContentView(view.view)
        presenter.onCreate()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }
}