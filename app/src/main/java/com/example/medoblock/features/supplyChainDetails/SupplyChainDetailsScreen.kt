package com.example.medoblock.features.supplyChainDetails

import android.Manifest
import android.content.pm.PackageManager
import android.util.Size
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.medoblock.core.QRCodeAnalyzer
import com.example.medoblock.core.utils.LoadingState
import com.example.medoblock.features.Screens
import com.example.medoblock.features.shared.components.MTopAppBar
import com.example.medoblock.features.shared.utils.LocalDimension
import com.example.medoblock.features.supplyChainDetails.components.CreateMedicine
import com.example.medoblock.features.supplyChainDetails.components.FdaAccept
import com.example.medoblock.features.supplyChainDetails.components.FdaReq
import com.example.medoblock.features.supplyChainDetails.components.RawMatAccept
import com.example.medoblock.features.supplyChainDetails.components.RawMatRequest
import com.example.medoblock.features.supplyChainDetails.components.SellMedicine
import com.example.medoblock.features.supplyChainDetails.components.TransCompleted
import com.example.medoblock.features.supplyChainDetails.components.TransMedCompleted
import com.example.medoblock.features.supplyChainDetails.components.TransOnWay
import com.example.medoblock.features.supplyChainDetails.components.TransReq
import com.example.medoblock.features.supplyChainDetails.components.TransReqAccept
import com.example.medoblock.features.supplyChainDetails.components.TransReqMed
import com.example.medoblock.features.supplyChainDetails.components.TransportInit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SupplyChainDetailsScreen(
    navController: NavController,
    viewModel: SupplyChainVM = hiltViewModel()
) {
    val dimension = LocalDimension.current
    val containerXPadding = dimension.ScreenHorizontalPadding

    val state by viewModel.state.collectAsState()
    val isLoading = state.loading
    var code by rememberSaveable{ mutableStateOf("") }
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
    var openCamera by rememberSaveable { mutableStateOf(false) }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    var hasCamPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted ->
            hasCamPermission = granted
        }
    )

    LaunchedEffect(key1 = true) {
        launcher.launch(Manifest.permission.CAMERA)
    }

    LaunchedEffect(key1 = Unit){
        viewModel.getSupplyChain("sdg")
    }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MTopAppBar(
                title = Screens.SupplyChainDetails.name,
                onBack = { navController.navigateUp() },
                scrollBehavior = scrollBehavior
            )
        },
    ) { paddingValues ->
        val topPadding = paddingValues.calculateTopPadding()

        Box{
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(start = containerXPadding - 8.dp, end = containerXPadding)
            ) {

                Spacer(modifier = Modifier.height(topPadding))
                if (hasCamPermission && openCamera) {
                    AndroidView(
                        factory = { context ->
                            val previewView = PreviewView(context)
                            val preview = Preview.Builder().build()
                            val selector = CameraSelector.Builder()
                                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                                .build()
                            preview.setSurfaceProvider(previewView.surfaceProvider)
                            val imageAnalysis = ImageAnalysis.Builder()
                                .setTargetResolution(
                                    Size(
                                        previewView.width,
                                        previewView.height
                                    )
                                )
                                .setBackpressureStrategy(STRATEGY_KEEP_ONLY_LATEST)
                                .build()
                            imageAnalysis.setAnalyzer(
                                ContextCompat.getMainExecutor(context),
                                QRCodeAnalyzer { result ->
                                    if(result.isNotEmpty()){
                                        code = result
                                        openCamera = false
                                        viewModel.getSupplyChain(result)
                                    }
                                }
                            )
                            try {
                                cameraProviderFuture.get().bindToLifecycle(
                                    lifecycleOwner,
                                    selector,
                                    preview,
                                    imageAnalysis
                                )
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                            previewView
                        },
                        modifier = Modifier.weight(1f)
                    )
                }

                if(!openCamera){
                    Spacer(modifier = Modifier.height(8.dp))
                    RawMatRequest(rawMatRequest = state.supplyChain?.rawMatRequest)
                    RawMatAccept(rawMatAccept = state.supplyChain?.rawMatAccept)
                    TransReq(transReq = state.supplyChain?.transReq)
                    TransReqAccept(transReqAccept = state.supplyChain?.transReqAccept)
                    TransportInit(data = state.supplyChain?.transportInit)
                    TransOnWay(data = state.supplyChain?.transOnWay)
                    TransCompleted(data = state.supplyChain?.transCompleted)
                    CreateMedicine(data = state.supplyChain?.createMedicne)
                    FdaReq(data = state.supplyChain?.fdaReq)
                    FdaAccept(data = state.supplyChain?.fdaAccept)
                    TransReqMed(data = state.supplyChain?.transReqMed)
                    TransMedCompleted(data = state.supplyChain?.transMedCompleted)
                    SellMedicine(data = state.supplyChain?.sellMedicine)
                }
            }

            if(isLoading == LoadingState.LOADING){
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                    contentAlignment = Alignment.Center
                ){
                    CircularProgressIndicator()
                }
            }
        }
    }
}